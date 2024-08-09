package com.example.nonameapp.ui

import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.data.repository.ProfileRepository
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.request.ChangePasswordRequest
import com.example.nonameapp.request.UpdateProfileRequest

class EditProfileViewModel : BaseViewModel() {
    private val profileRepository = ProfileRepository(RetrofitClient.apiService)

    fun updateProfile(accessToken: String, updateProfileRequest: UpdateProfileRequest) {
        executeTask(
            request = {
                profileRepository.updateProfile(accessToken, updateProfileRequest)
            },
            onSuccess = {
            },
            onError = {
            }
        )
    }

    fun changePassword(accessToken: String, changePasswordRequest: ChangePasswordRequest) {
        executeTask(
            request = {
                profileRepository.changePassword(accessToken, changePasswordRequest)
            },
            onSuccess = {},
            onError = {}
        )
    }
}