package com.example.nonameapp.data.source.network

data class ApiResponse<T>(
    val status: String,
    val message: String,
    val data: Any? = null
)
