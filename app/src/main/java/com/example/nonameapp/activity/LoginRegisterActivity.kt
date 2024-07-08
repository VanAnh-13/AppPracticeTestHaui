package com.example.nonameapp.activity

import androidx.fragment.app.commit
import com.example.nonameapp.base.BaseActivity
import com.example.nonameapp.databinding.ActivityLoginRegisterBinding
import com.example.nonameapp.ui.LoginFragment

class LoginRegisterActivity :
    BaseActivity<ActivityLoginRegisterBinding>(ActivityLoginRegisterBinding::inflate) {
    override fun initData() {
        supportFragmentManager.commit {
            replace(binding.fragmentLoginRegisterContainer.id, LoginFragment())
            setReorderingAllowed(true)
        }
    }

    override fun bindData() {
    }

    override fun setOnClick() {
    }

}