package com.example.nonameapp.model

data class Test (
    val _id: String,
    val name: String,
    val duration: Int,
    val questions: List<QuestionT>,
    val subject: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
)