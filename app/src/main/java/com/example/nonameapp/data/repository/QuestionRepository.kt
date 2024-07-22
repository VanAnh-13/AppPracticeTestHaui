package com.example.nonameapp.data.repository

import com.example.nonameapp.base.BaseRepository
import com.example.nonameapp.base.DataState
import com.example.nonameapp.data.source.network.ApiService
import com.example.nonameapp.request.QuestionRequest
import com.example.nonameapp.response.ApiResponse
import com.example.nonameapp.response.QuestionsResponse

class QuestionRepository(
    private val apiService: ApiService
) : BaseRepository() {
    suspend fun getQuestions(accessToken: String, subjectId: String) : DataState<ApiResponse<QuestionsResponse>> {
        return getResult {
            apiService.getQuestionsBySubjectId(accessToken, subjectId)
        }
    }
}