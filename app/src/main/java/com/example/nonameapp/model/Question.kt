package com.example.nonameapp.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Question(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("subject")
    val subject: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("answers")
    val answers: List<String>,
    @SerializedName("correctAnswer")
    val correctAnswer: String,
    @SerializedName("createAt")
    val createAt: Date,
    @SerializedName("updateAt")
    val updateAt: Date
)
