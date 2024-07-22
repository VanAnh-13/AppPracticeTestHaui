package com.example.nonameapp.response

data class ApiResponse<T>(
    val message: String,
    val code: Int,
    val data: T
)
