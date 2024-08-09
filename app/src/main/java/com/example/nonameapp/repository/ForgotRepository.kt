package com.example.nonameapp.repository

import com.example.nonameapp.base.BaseRepository
import com.example.nonameapp.data.source.network.ApiService
import com.example.nonameapp.request.ChangePasswordRequest
import com.example.nonameapp.request.ForgotPasswordRequest
import com.example.nonameapp.request.RequestChangePassword

class ForgotRepository(
    private val apiService: ApiService,
) : BaseRepository() {
    suspend fun getOtp(forgotPassWordRequest: ForgotPasswordRequest) = getResult {
        apiService.getOtp(request = forgotPassWordRequest)
    }

    suspend fun verifyOtp(forgotPassWordRequest: ForgotPasswordRequest) = getResult {
        apiService.verifyOtp(request = forgotPassWordRequest)
    }

    suspend fun changePassword(changePasswordRequest: RequestChangePassword) = getResult {
        apiService.changePassword(changePasswordRequest = changePasswordRequest)
    }
}