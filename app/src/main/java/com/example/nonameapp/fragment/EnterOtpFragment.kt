package com.example.nonameapp.fragment

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentEnterOtpBinding
import com.example.nonameapp.request.ForgotPasswordRequest

class EnterOtpFragment : BaseFragment<FragmentEnterOtpBinding>(FragmentEnterOtpBinding::inflate) {
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
            val otp = binding.edtEnterOtp.text.toString()

            if (otp.isEmpty()) {
                Toast.makeText(requireContext(), "OTP cannot empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                viewModel.verifyOtp(
                    request = ForgotPasswordRequest(
                        email = ForgotPasswordFragment.emailChangPassword,
                        otp = otp
                    ),
                    onSuccess = { message ->
                        if (message == "OTP hợp lệ") {
                            findNavController().navigate(R.id.otp_to_changPass)
                        }
                    },
                    onError = {
                        Toast.makeText(requireContext(), "OTP không hợp lệ", Toast.LENGTH_SHORT)
                            .show()
                    },
                )
            }
        }
    }

}