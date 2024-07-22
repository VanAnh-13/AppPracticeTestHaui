package com.example.nonameapp.response

import com.example.nonameapp.model.QuestionsT
data class QuestionsTResponse (
    val name : String,
    val questionsT: List<QuestionsT>
)