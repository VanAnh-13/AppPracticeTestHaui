package com.example.nonameapp

import androidx.fragment.app.commit
import com.example.nonameapp.base.BaseActivity
import com.example.nonameapp.databinding.ActivityMainBinding
import com.example.nonameapp.ui.LoginFragment
import com.example.nonameapp.ui.SignUpFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun initData() {
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, LoginFragment())
            setReorderingAllowed(true)
        }
    }

    override fun bindData() {
    }

    override fun setOnClick() {
    }
}