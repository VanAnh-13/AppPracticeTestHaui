package com.example.nonameapp.ui

import androidx.lifecycle.ViewModelProvider
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentTestBinding

class TestFragment :
    BaseFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

    override val viewModel: TestViewModel
        get() = ViewModelProvider(this)[TestViewModel::class.java]

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun bindData() {
        TODO("Not yet implemented")
    }

    override fun observeData() {
        TODO("Not yet implemented")
    }

    override fun setOnClick() {
        TODO("Not yet implemented")
    }
}

