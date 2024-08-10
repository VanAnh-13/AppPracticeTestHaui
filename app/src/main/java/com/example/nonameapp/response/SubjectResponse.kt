package com.example.nonameapp.response

import com.example.nonameapp.model.Subject

data class SubjectResponse(
    val subjects: List<Subject>,
)

data class TestResponse(
    val test: List<Subject>
)
