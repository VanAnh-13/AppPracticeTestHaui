package com.example.nonameapp.ui

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
    }
}
