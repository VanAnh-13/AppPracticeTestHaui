package com.example.nonameapp.ui

import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.data.repository.AuthRepository
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.response.LoginResponse
import com.example.nonameapp.request.LoginRequest

class LoginViewModel : BaseViewModel() {
    private val authRepository = AuthRepository(RetrofitClient.apiService)
    fun login(
        loginRequest: LoginRequest,
        onLoginSuccess: (LoginResponse) -> Unit,
        onLoginError: (Exception) -> Unit
    ) {
        executeTask(
            request = {
                authRepository.login(loginRequest)
            },
            onSuccess = {
                onLoginSuccess.invoke(it.data)
            },
            onError = {
                onLoginError.invoke(it)
            }
        )
    }
}