package com.example.nonameapp.request

data class ChangePasswordRequest(
    val oldPassword: String,
    val newPassword: String,
    val confirmPassword: String
)

data class RequestChangePassword (
    val email: String,
    val password: String,
    val confirmPassword: String
)