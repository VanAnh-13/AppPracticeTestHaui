package com.example.nonameapp.response

import com.example.nonameapp.model.User

data class LoginResponse(
    val user: User,
    val accessToken: String
)
