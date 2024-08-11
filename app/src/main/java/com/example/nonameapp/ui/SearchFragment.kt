package com.example.nonameapp.ui

import android.app.Dialog
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nonameapp.R
import com.example.nonameapp.adapter.SearchAdapter
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.data.source.local.SharedPreferencesManager
import com.example.nonameapp.databinding.CreatePostLayoutBinding
import com.example.nonameapp.databinding.FragmentSearchBinding
import com.example.nonameapp.request.CreatePostRequest
import java.io.File

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override val viewModel: SearchViewModel
        get() = ViewModelProvider(this)[SearchViewModel::class.java]

    private lateinit var searchAdapter: SearchAdapter
    private var searchHandler: Handler? = null
    private var searchRunnable: Runnable? = null

    private var uri: Uri? = null
    private lateinit var imageView: ImageView

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Glide.with(this)
                    .load(uri)
                    .circleCrop()
                    .into(imageView)
            }
        }

    override fun initData() {
        searchHandler = Handler(Looper.getMainLooper())
    }

    override fun bindData() {
        searchAdapter = SearchAdapter()
        binding.searchResults.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }

    override fun onDetach() {
        super.onDetach()


    }

    override fun observeData() {
        viewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            searchAdapter.setResultList(searchResults)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setOnClick() {
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Hủy bỏ việc gọi API trước đó nếu người dùng tiếp tục gõ
                searchRunnable?.let { searchHandler?.removeCallbacks(it) }

                // Tạo một Runnable mới để gọi API sau khi người dùng ngừng gõ
                searchRunnable = Runnable {
                    SharedPreferencesManager.getToken(requireContext())?.let { token ->
                        if (s.isNullOrBlank().not()) {
                            viewModel.search(
                                accessToken = token,
                                query = s.toString()
                            )
                        } else {
                            Toast.makeText(context, "Không có dữ liệu", Toast.LENGTH_SHORT).show()
                        }
                    } ?: run {
                        Toast.makeText(context, "Token is missing", Toast.LENGTH_SHORT).show()
                    }
                }

                // Thực hiện cuộc gọi API sau khoảng thời gian delay (300ms)
                searchHandler?.postDelayed(searchRunnable!!, 300)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.askQuestionButton.setOnClickListener {
            showDialogue()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDialogue() {
        val dialogue = Dialog(requireContext())

        dialogue.setCancelable(true)
        val binding = CreatePostLayoutBinding.inflate(layoutInflater)
        dialogue.setContentView(binding.root)

        dialogue.window?.setBackgroundDrawableResource(android.R.color.transparent)


        val content = binding.edtContent
        this.imageView = binding.cratePostImage
        val btnExit = binding.btnExit
        val btnSuccess = binding.btnNo

        val token = SharedPreferencesManager.getToken(requireContext())
        val userId = SharedPreferencesManager.getUserInfo(requireContext())!!

        this.imageView.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        btnSuccess.setOnClickListener {
            val createPostRequest = CreatePostRequest(
                title = content.text.toString(),
                user = userId._id,
                image = uriToFile(uri = uri)?.path
            )

            viewModel.createPost(
                accessToken = token!!,
                createPostRequest = createPostRequest,
                onCreateSuccess = { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                },
                onCreateError = {
                    Toast.makeText(requireContext(), "Create post error !", Toast.LENGTH_SHORT)
                        .show()
                }
            )
            dialogue.dismiss()
        }

        btnExit.setOnClickListener {
            dialogue.dismiss()
        }

        // Set custom size for the dialog
        dialogue.show()
        val window: Window? = dialogue.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun uriToFile(uri: Uri?): File? {
        uri ?: return null
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireContext().contentResolver.query(
            uri,
            filePathColumn,
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val filePath = columnIndex?.let { cursor.getString(it) }
        cursor?.close()
        return filePath?.let { File(it) }
    }
}