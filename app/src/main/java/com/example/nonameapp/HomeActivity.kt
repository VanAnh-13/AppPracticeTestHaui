package com.example.nonameapp

import androidx.fragment.app.commit
import com.example.nonameapp.base.BaseActivity
import com.example.nonameapp.databinding.ActivityHomeBinding
import com.example.nonameapp.ui.TestFragment


class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    override fun initData() {
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, TestFragment())
            setReorderingAllowed(true)
        }
    }

    override fun bindData() {
    }

    override fun setOnClick() {
    }

}