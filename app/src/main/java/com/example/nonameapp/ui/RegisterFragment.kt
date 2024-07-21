package com.example.nonameapp.ui

import android.annotation.SuppressLint
import android.text.InputType
import android.view.MotionEvent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentSignUpBinding
import com.example.nonameapp.request.RegisterRequest
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {
    override val viewModel: RegisterViewModel
        get() = ViewModelProvider(this)[RegisterViewModel::class.java]

    override fun initData() {
    }

    override fun bindData() {
    }

    override fun observeData() {
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
        // Handle password visibility toggle
        setUpPasswordVisibilityToggle()
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun setUpPasswordVisibilityToggle() {
        val togglePasswordVisibility = { editText: TextInputEditText ->
            val isPasswordVisible = editText.inputType and InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            if (isPasswordVisible) {
                // Hide password
                editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0)
            } else {
                // Show password
                editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0)
            }
            editText.setSelection(editText.text?.length ?: 0) // Move cursor to the end of the text
        }
        binding.edtPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableRight = 2
                if (event.rawX >= (binding.edtPassword.right - binding.edtPassword.compoundDrawables[drawableRight].bounds.width())) {
                    togglePasswordVisibility(binding.edtPassword)
                    return@setOnTouchListener true
                }
            }
            false
        }

        binding.edtConfirmPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableRight = 2
                if (event.rawX >= (binding.edtConfirmPassword.right - binding.edtConfirmPassword.compoundDrawables[drawableRight].bounds.width())) {
                    togglePasswordVisibility(binding.edtConfirmPassword)
                    return@setOnTouchListener true
                }
            }
            false
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

    private fun signUp(fullname: String, email: String, password: String) {
        viewModel.register(
            registerRequest = RegisterRequest(fullname, email, password),
            onRegisterSuccess = {
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, LoginFragment())
                    .commit()
                Toast.makeText(requireContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show()

            },
            onRegisterError = {
//                Toast.makeText(requireContext(), "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                println("Login error: ${it.message}")
            })
    }
}