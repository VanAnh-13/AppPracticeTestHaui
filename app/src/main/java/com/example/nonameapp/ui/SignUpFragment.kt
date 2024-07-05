package com.example.nonameapp.ui

import android.widget.Toast
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
            val name = binding.edtUserName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            val confirmPassword = binding.edtConfirmPassword.text.toString().trim()

            // Check the validity of the data
            if (validateInputs(name, email, password, confirmPassword)) {
                signUp(name, email, password)
            }
        }
    }

    private fun validateInputs(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (name.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter your name", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(requireContext(), "Invalid email address", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isEmpty() || password.length < 6) {
            Toast.makeText(
                requireContext(),
                "Password must be at least 6 characters long",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if (confirmPassword.isEmpty() || confirmPassword != password) {
            Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun signUp(name: String, email: String, password: String) {
        // Gọi API đăng ký từ backend thông qua Retrofit
//        val call = apiService.signUp(name, email, password)
//        call.enqueue(object : Callback<Void> {
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                if (response.isSuccessful) {
//                    // Đăng ký thành công, bạn có thể thực hiện các hành động tiếp theo như chuyển hướng đến màn hình chính
//                    Toast.makeText(requireContext(), "Sign up successful", Toast.LENGTH_SHORT).show()
//                    navigateToMainScreen()
//                } else {
//                    // Đăng ký thất bại, xử lý lỗi tại đây (ví dụ: hiển thị thông báo lỗi từ server)
//                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
//                    Toast.makeText(requireContext(), "Sign up failed: $errorMessage", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Void>, t: Throwable) {
//                // Xử lý khi gọi API thất bại (ví dụ: hiển thị thông báo lỗi mạng)
//                Toast.makeText(requireContext(), "Sign up failed: ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    private fun navigateToMainScreen() {
//         val transaction = requireActivity().supportFragmentManager.beginTransaction()
//         transaction.replace(R.id.fragment_container, MainFragment())
//         transaction.commit()
    }
}