package com.example.nonameapp.data.source.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

//    @POST("")
//    fun checkEmail(@Body body: Map<String, String>): Call<ApiResponse<Any>>

    @POST("/api/v1/auth/register")
    fun register(@Body body: Map<String, String>): Call<ApiResponse<Any>>
}