package com.example.nonameapp.response

import com.example.nonameapp.model.Question

data class SearchResponse (
    val data: List<Question>
)