package com.example.nonameapp.model

data class DataQuestion(
    val question: List<Question>,
    val limit: Int,
    val currentPage: Int,
    val totalPage: Int,
    val totalResults: Int
)
