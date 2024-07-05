package com.example.nonameapp.ui

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.databinding.FragmentLoginBinding
import retrofit2.Callback
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.model.LoginResponse
import retrofit2.Call
import retrofit2.Response
import android.content.Context
import com.example.nonameapp.request.LoginRequest

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val myViewModel: BaseViewModel by viewModels()
    override val viewModel: BaseViewModel
        get() = myViewModel

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
                    Toast.makeText(requireContext(), "Enter your password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.registerTextView.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, SignUpFragment())
            transaction.addToBackStack(null)
            transaction.commit()
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
        val call = RetrofitClient.apiService.login(loginRequest = LoginRequest(email, password))

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        saveAccessToken(loginResponse.data.accessToken)
                    }
                    Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Login failed: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun saveAccessToken(accessToken: String) {
        val sharedPreferences =
            requireContext().getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("access_token", accessToken)
            apply()
        }
        Toast.makeText(requireContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
    }
}
