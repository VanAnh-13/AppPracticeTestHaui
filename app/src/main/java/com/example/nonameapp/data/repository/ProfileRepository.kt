package com.example.nonameapp.data.repository

import com.example.nonameapp.base.BaseRepository
import com.example.nonameapp.data.source.network.ApiService
import com.example.nonameapp.request.ChangePasswordRequest
import com.example.nonameapp.request.UpdateProfileRequest

class ProfileRepository(private val apiService: ApiService) : BaseRepository() {
    suspend fun updateProfile(accessToken: String, profileRequest: UpdateProfileRequest) = getResult {
        getResult {
            apiService.updateProfile(accessToken, profileRequest)
        }
    }

    suspend fun changePassword(accessToken: String, changePasswordRequest: ChangePasswordRequest) = getResult {
        getResult {
            apiService.changePassword(accessToken, changePasswordRequest)
        }

    }
}