package com.example.nonameapp.model

data class QuestionT(
    val _id: String,
    val content: String,
    val answers: List<String>,
    val correctAnswer: String,
    val __v: Int
)