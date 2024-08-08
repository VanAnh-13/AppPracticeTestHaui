package com.example.nonameapp.model

import com.google.gson.annotations.SerializedName

data class Subject(
    @SerializedName("_id")
    val id: String,

    @SerializedName("name")
    val name: String
)
