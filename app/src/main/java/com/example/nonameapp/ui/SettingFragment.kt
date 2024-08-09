package com.example.nonameapp.ui

import androidx.lifecycle.ViewModelProvider
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentSettingBinding

class SettingFragment() : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    override val viewModel: SettingViewModel
        get() = ViewModelProvider(this)[SettingViewModel::class.java]

    override fun initData() {
    }

    override fun bindData() {
    }

    override fun observeData() {
    }

    override fun setOnClick() {
//        binding.settingAccount.setOnClickListener {
//
//        }
    }
}