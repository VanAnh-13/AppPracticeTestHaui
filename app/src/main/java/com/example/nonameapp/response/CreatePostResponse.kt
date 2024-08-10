package com.example.nonameapp.response

import com.example.nonameapp.model.Post

data class CreatePostResponse(
    val post: List<Post>
)