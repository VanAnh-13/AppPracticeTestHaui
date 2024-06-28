package com.example.nonameapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.databinding.FragmentSignUpBinding

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

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
        binding.btnSignUp.setOnClickListener {
            val name = binding.edtUserName.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtConfirmPassword.text.toString()
            signUp(
                name = name,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )
        }
    }
    private fun signUp(
        email: String = "",
        name: String = "",
        password: String = "",
        confirmPassword: String = ""
    ){}
}