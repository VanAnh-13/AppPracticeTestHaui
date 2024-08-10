package com.example.nonameapp.fragment

import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.repository.ForgotRepository
import com.example.nonameapp.request.ChangePasswordRequest
import com.example.nonameapp.request.ForgotPasswordRequest
import com.example.nonameapp.request.RequestChangePassword

class ForgotPasswordModel : BaseViewModel() {
    companion object {
        var otpError = ""
        var passwordError = ""
    }

    private val forgotPasswordRepository by lazy {
        ForgotRepository(
            RetrofitClient.apiService
        )
    }

    fun getOtp(
        request: ForgotPasswordRequest,
        onSuccess: (String) -> Unit,
        onError: (Exception) -> Unit
    ) = executeTask(
        request = {
            forgotPasswordRepository.getOtp(forgotPassWordRequest = request)
        },
        onSuccess = {
            onSuccess.invoke(it.message)
        },
        onError = {
            onError.invoke(it)
        },
    )

    fun verifyOtp(
        request: ForgotPasswordRequest,
        onSuccess: (String) -> Unit,
        onError: (Exception) -> Unit
    ) = executeTask(
        request = {
            forgotPasswordRepository.verifyOtp(forgotPassWordRequest = request)
        },
        onSuccess = { response ->
            onSuccess.invoke(response.message)
        },
        onError = {
            onError.invoke(it)
        },
    )

    fun changePassword(
        request: RequestChangePassword,
        onSuccess: (String) -> Unit,
        onError: (Exception) -> Unit
    ) = executeTask(
        request = {
            forgotPasswordRepository.changePassword(changePasswordRequest = request)
        },
        onSuccess = {
            onSuccess.invoke(it.message)
        },
        onError = {
            onError.invoke(it)
        }
    )
}