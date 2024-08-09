package com.example.nonameapp.request

data class ForgotPasswordRequest(
    val email: String,
    val otp: String? = null
)