package com.example.nonameapp.ui

import android.widget.Toast
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentLoginBinding
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nonameapp.activity.HomeActivity
import com.example.nonameapp.activity.MainActivity
import com.example.nonameapp.request.LoginRequest

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override val viewModel: LoginViewModel
        get() = ViewModelProvider(this)[LoginViewModel::class.java]

    override fun initData() {
    }

    override fun bindData() {
    }

    override fun observeData() {
    }

    override fun setOnClick() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (validate(email, password)) {
                performLogin(email, password)
            } else {
                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches()
                ) {
                    Toast.makeText(requireContext(), "Invalid email", Toast.LENGTH_SHORT).show()
                }
                if (password.isEmpty()) {
                    Toast.makeText(requireContext(), "Enter your password", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment2)
        }
    }

    // Validate dữ liệu nhập vào
    private fun validate(email: String, password: String): Boolean {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }
        if (password.isEmpty()) {
            return false
        }
        return true
    }

    // Logic Đăng nhập
    private fun performLogin(email: String, password: String) {
        viewModel.login(
            loginRequest = LoginRequest(email, password),
            onLoginSuccess = {
                saveAccessToken(it.accessToken)
                startActivity(Intent(requireContext(), HomeActivity::class.java))
                requireActivity().finish()
            },
            onLoginError = {
                Toast.makeText(requireContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show()
                println("Login error: ${it.message}")
            }
        )
    }

    // Lưu token vào SharedPreferences
    private fun saveAccessToken(accessToken: String) {
        val sharedPreferences =
            requireContext().getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("access_token", "Bearer $accessToken")
            apply()
        }
        Toast.makeText(requireContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
    }
}
