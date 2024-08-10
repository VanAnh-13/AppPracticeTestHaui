package com.example.nonameapp.ui

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonameapp.adapter.SearchAdapter
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.data.source.local.SharedPreferencesManager
import com.example.nonameapp.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override val viewModel: SearchViewModel
        get() = ViewModelProvider(this)[SearchViewModel::class.java]

    private lateinit var searchAdapter: SearchAdapter
    private var searchHandler: Handler? = null
    private var searchRunnable: Runnable? = null

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
    }
}
