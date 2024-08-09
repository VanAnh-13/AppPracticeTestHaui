package com.example.nonameapp.request

import java.io.File

data class UpdateProfileRequest(
    val fullName: String?,
    val link: String?,
    val avatar: File?
)