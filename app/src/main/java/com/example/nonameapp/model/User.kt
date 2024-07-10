package com.example.nonameapp.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class User(
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("fullname")
    val fullName: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: Date,
    @SerializedName("isLocked")
    val isLocked: Boolean,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("star")
    val star: Int,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("updatedAt")
    val updatedAt: Date,
    @SerializedName("password")
    val password: String
)
