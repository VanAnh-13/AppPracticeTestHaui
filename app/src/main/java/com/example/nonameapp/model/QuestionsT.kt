package com.example.nonameapp.model

import com.google.gson.annotations.SerializedName

data class QuestionsT(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("answers")
    val answers: List<String>,
    @SerializedName("correctAnswer")
    val correctAnswer: String,
    @SerializedName("__v")
    val __v: Int
)