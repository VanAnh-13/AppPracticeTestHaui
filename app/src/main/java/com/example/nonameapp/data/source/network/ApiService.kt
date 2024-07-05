package com.example.nonameapp.data.source.network

import com.example.nonameapp.request.LoginRequest
import com.example.nonameapp.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}