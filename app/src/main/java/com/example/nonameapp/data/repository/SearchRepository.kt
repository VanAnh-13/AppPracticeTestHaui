package com.example.nonameapp.data.repository

import com.example.nonameapp.base.BaseRepository
import com.example.nonameapp.base.DataState
import com.example.nonameapp.data.source.network.ApiService
import com.example.nonameapp.response.ApiResponse
import com.example.nonameapp.response.SearchResponse

class SearchRepository(private val apiService: ApiService) : BaseRepository() {
    suspend fun searchQuestions(token: String, query: String): DataState<ApiResponse<SearchResponse>> {
        return getResult {
            apiService.searchQuestions(token, query)
        }
    }
}