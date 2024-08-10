package com.example.nonameapp.data.repository

import com.example.nonameapp.base.BaseRepository
import com.example.nonameapp.data.source.network.ApiService

class TestRepository(private val apiService: ApiService) : BaseRepository() {
    suspend fun getTest(id: String) = getResult {
        apiService.getTestBySubjectId(subjectId = id)
    }
}