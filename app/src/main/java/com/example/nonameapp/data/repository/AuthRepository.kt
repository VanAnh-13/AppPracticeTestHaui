package com.example.nonameapp.data.repository

import com.example.nonameapp.base.BaseRepository
import com.example.nonameapp.base.DataState
import com.example.nonameapp.data.source.network.ApiService
import com.example.nonameapp.response.ApiResponse
import com.example.nonameapp.response.LoginResponse
import com.example.nonameapp.request.LoginRequest
import com.example.nonameapp.request.RegisterRequest
import com.example.nonameapp.response.RegisterResponse

class AuthRepository(
    private val apiService: ApiService,
) : BaseRepository() {
    suspend fun login(loginRequest: LoginRequest): DataState<ApiResponse<LoginResponse>> {
        return getResult{
            apiService.login(loginRequest)
        }
    }

    suspend fun register(registerRequest: RegisterRequest): DataState<ApiResponse<RegisterResponse>> {
        return getResult {
            apiService.register(registerRequest)
        }
    }
}