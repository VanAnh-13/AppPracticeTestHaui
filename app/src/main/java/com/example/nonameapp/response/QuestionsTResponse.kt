package com.example.nonameapp.response

import com.example.nonameapp.model.QuestionsT

data class Test(
    val questions: List<QuestionsT>
)

data class QuestionsTResponse(
    val test: Test
)