package com.example.nonameapp.ui

import androidx.lifecycle.ViewModelProvider
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentSearchBinding


class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override val viewModel: SearchViewModel
        get() = ViewModelProvider(this)[SearchViewModel::class.java]

    override fun initData() {
    }

    override fun bindData() {

    }

    override fun observeData() {

    }

    override fun setOnClick() {

    }

}