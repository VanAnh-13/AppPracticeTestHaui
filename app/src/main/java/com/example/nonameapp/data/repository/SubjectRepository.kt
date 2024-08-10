package com.example.nonameapp.data.repository

import com.example.nonameapp.base.BaseRepository
import com.example.nonameapp.data.source.network.ApiService

class SubjectRepository(
    private val apiService: ApiService
) : BaseRepository() {

    suspend fun getAllSubject(accessToken: String) =
        getResult {
            apiService.getListSubject(accessToken = accessToken)
        }
}