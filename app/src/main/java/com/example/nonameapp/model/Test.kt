package com.example.nonameapp.model

data class Test (
    val _id: String,
    val name: String,
    val duration: Int,
    val questions: List<QuestionsT>,
    val subject: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
)