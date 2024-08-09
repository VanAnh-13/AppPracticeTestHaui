package com.example.nonameapp.repository

import com.example.nonameapp.base.BaseRepository
import com.example.nonameapp.base.DataState
import com.example.nonameapp.data.source.network.ApiService
import com.example.nonameapp.response.ApiResponse
import com.example.nonameapp.response.SearchResponse

class SearchRepository(private val apiService: ApiService) : BaseRepository() {
    suspend fun searchQuestions(token: String, query: String): DataState<ApiResponse<SearchResponse>> {
        return try {
            val response = apiService.searchQuestions(token, query)
            DataState.Success(response)
        } catch (e: Exception) {
            DataState.Error(e)
        }
    }
}
