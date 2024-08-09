package com.example.nonameapp.fragment

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.nonameapp.activity.HomeActivity
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.databinding.FragmentChangePasswordBinding
import com.example.nonameapp.request.ChangePasswordRequest
import com.example.nonameapp.request.RequestChangePassword

class ChangePasswordFragment :
    BaseFragment<FragmentChangePasswordBinding>(FragmentChangePasswordBinding::inflate) {
    override val viewModel: ForgotPasswordModel
        get() = ViewModelProvider(this)[ForgotPasswordModel::class.java]

    override fun initData() {
    }

    override fun bindData() {
    }

    override fun observeData() {
    }

    override fun setOnClick() {
        binding.btnChangePassword.setOnClickListener {
            val passwords = binding.edtNewPassword.text.toString()
            val confirmPassword = binding.edtSubmitPassword.text.toString()

            if (passwords != confirmPassword) {
                Toast.makeText(requireContext(), "Password does not match", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                viewModel.changePassword(
                    request = RequestChangePassword(
                        email = ForgotPasswordFragment.emailChangPassword,
                        password = passwords,
                        confirmPassword = confirmPassword
                    ),
                    onSuccess = {
                        if (it == "Đặt lại mật khẩu thành công") {
                            startActivity(Intent(requireActivity(), HomeActivity::class.java))
                            Toast.makeText(
                                requireContext(),
                                "Change password success",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    onError = {
                        Toast.makeText(requireContext(), "Invalid Password", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}