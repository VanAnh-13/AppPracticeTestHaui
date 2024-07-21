package com.example.nonameapp.data.source.network

import com.example.nonameapp.request.LoginRequest
import com.example.nonameapp.request.RegisterRequest
import com.example.nonameapp.response.ApiResponse
import com.example.nonameapp.response.LoginResponse
import com.example.nonameapp.response.QuestionsResponse
import com.example.nonameapp.response.RegisterResponse
import com.example.nonameapp.response.SubjectResponse
import com.example.nonameapp.ui.TestViewModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/api/v1/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): ApiResponse<LoginResponse>
    @POST("/api/v1/auth/register")
    fun register(@Body registerRequest: RegisterRequest): ApiResponse<RegisterResponse>

    @GET("/api/v1/subjects/")
    suspend fun getListSubject(
        @Header("Authorization") accessToken: String
    ): ApiResponse<SubjectResponse>

    @GET("/api/v1/questions")
    suspend fun getQuestionsBySubjectId(
        @Header("Authorization") accessToken: String,
        @Header("_id") subjectId: String
    ): ApiResponse<QuestionsResponse>

    @GET("/api/v1/tests")
    suspend fun getTestByTestId(@Header("_id") testId: String): ApiResponse<TestViewModel>
}
