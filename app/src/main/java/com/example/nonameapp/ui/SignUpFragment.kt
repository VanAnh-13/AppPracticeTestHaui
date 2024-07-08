package com.example.nonameapp.ui

import RegisterResponse
import android.annotation.SuppressLint
import android.content.Intent
import android.text.InputType
import android.view.MotionEvent
import android.widget.Toast
import com.example.nonameapp.R
import com.example.nonameapp.activity.MainActivity
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.databinding.FragmentSignUpBinding
import com.example.nonameapp.request.RegisterRequest
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment() : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

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
            navigateToHomeScreen()
        }
        // Handle password visibility toggle
        setUpPasswordVisibilityToggle()

    }
    @SuppressLint("ClickableViewAccessibility")
    private fun setUpPasswordVisibilityToggle() {
        val togglePasswordVisibility = { editText: TextInputEditText ->
            // Check the display status of the password return true if the password is visible,  false if it is hidden.
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

    private fun signUp(name: String, email: String, password: String) {
        val call = RetrofitClient.apiService.register(
            registerRequest = RegisterRequest(
                email,
                password,
                password
            )
        )
        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireContext(), "Sign up failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Login failed: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun navigateToHomeScreen() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}


