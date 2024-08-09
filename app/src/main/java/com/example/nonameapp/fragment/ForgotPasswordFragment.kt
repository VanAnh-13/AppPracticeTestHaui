package com.example.nonameapp.fragment

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentForgotPasswordBinding
import com.example.nonameapp.request.ForgotPasswordRequest

class ForgotPasswordFragment :
    BaseFragment<FragmentForgotPasswordBinding>(FragmentForgotPasswordBinding::inflate) {
    override val viewModel: ForgotPasswordModel
        get() = ViewModelProvider(this)[ForgotPasswordModel::class.java]

    companion object {
        var emailChangPassword = ""
    }

    override fun initData() {
    }

    override fun bindData() {
    }

    override fun observeData() {
    }

    override fun setOnClick() {
        binding.btnGetOtp.setOnClickListener {
            val email = binding.edtEnterEmail.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Email is not empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (isValidEmail(email = email)) {
                Toast.makeText(requireContext(), "Invalid email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                viewModel.getOtp(
                    request = ForgotPasswordRequest(
                        email = email
                    ),
                    onSuccess = {
                        emailChangPassword = email
                        findNavController().navigate(R.id.email_to_otp)
                    },
                    onError = {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        return@getOtp
                    }
                )
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$".toRegex()
        return !emailRegex.matches(email)
    }

}