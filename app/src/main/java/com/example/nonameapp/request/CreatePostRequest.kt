package com.example.nonameapp.request

import java.io.File

data class CreatePostRequest(
    val title: String,
    val user: String,
    val image: File? = null
)