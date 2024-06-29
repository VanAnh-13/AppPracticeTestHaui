package com.example.nonameapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.databinding.FragmentLoginBinding

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
                // Xử lý logic đăng nhập
                Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
            } else {
                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
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
}
