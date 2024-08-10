package com.example.nonameapp.ui

import android.app.Dialog
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonameapp.R
import com.example.nonameapp.adapter.SearchAdapter
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.data.source.local.SharedPreferencesManager
import com.example.nonameapp.databinding.FragmentSearchBinding
import com.example.nonameapp.request.CreatePostRequest
import java.io.File

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override val viewModel: SearchViewModel
        get() = ViewModelProvider(this)[SearchViewModel::class.java]

    private lateinit var searchAdapter: SearchAdapter

    private var uri: Uri? = null

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        val galleryUri = it
        try {
            uri = galleryUri
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initData() {
        searchAdapter = SearchAdapter()
    }

    override fun bindData() {
        binding.searchResults.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }

    override fun observeData() {
        viewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            searchAdapter.setResultList(searchResults)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    override fun setOnClick() {
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                SharedPreferencesManager.getToken(requireContext())?.let {
                    viewModel.search(
                        accessToken = it,
                        query = s.toString()
                    )
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.askQuestionButton.setOnClickListener {
            showDialogue()
        }
    }

    private fun showDialogue() {
        val dialogue = Dialog(requireContext())

        dialogue.setCancelable(true)
        dialogue.setContentView(R.layout.create_post_layout)

        val content = dialogue.findViewById<EditText>(R.id.edtContent)
        val image = dialogue.findViewById<ImageView>(R.id.cratePostImage)
        val btnExit = dialogue.findViewById<Button>(R.id.btn_exit)
        val btnSuccess = dialogue.findViewById<Button>(R.id.btn_no)

        val token = SharedPreferencesManager.getToken(requireContext())
        val userId = SharedPreferencesManager.getUserInfo(requireContext())?.id

        image.setOnClickListener {
            galleryLauncher.launch("image/*")
            image.setImageURI(uri)
        }

        btnSuccess.setOnClickListener {
            val createPostRequest = userId?.let { it1 ->
                it1
                CreatePostRequest(
                    title = content.text.toString(),
                    user = it1,
                    image = uriToFile(uri = uri)
                )
            }

            if (createPostRequest != null) {
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
            }
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