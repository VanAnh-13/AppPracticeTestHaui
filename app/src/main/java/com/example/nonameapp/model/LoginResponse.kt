package com.example.nonameapp.model

data class LoginResponse(
    val message: String,
    val code: Int,
    val data: Data,
)
