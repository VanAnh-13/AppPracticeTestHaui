package com.example.nonameapp.model

data class ApiResponse<T>(
    val message: String,
    val code: Int,
    val data: T
)
