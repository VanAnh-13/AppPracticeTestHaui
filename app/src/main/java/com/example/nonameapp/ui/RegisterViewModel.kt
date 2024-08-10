package com.example.nonameapp.ui

import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.data.repository.AuthRepository
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.request.RegisterRequest

class RegisterViewModel : BaseViewModel(){
    private val authRepository = AuthRepository(RetrofitClient.apiService)
    fun register(
        registerRequest: RegisterRequest,
        onRegisterSuccess: (Any) -> Unit,
        onRegisterError: (Exception) -> Unit
    ) {
        executeTask(
            request = {
                authRepository.register(registerRequest)
            },
            onSuccess = {
                onRegisterSuccess.invoke(it.data)
            },
            onError = {
                onRegisterError.invoke(it)
            }
        )
    }

}