package com.example.nonameapp.ui

import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.data.repository.AuthRepository
import com.example.nonameapp.data.source.network.ApiService
import com.example.nonameapp.data.source.network.RetrofitClient

class TestViewModel : BaseViewModel() {
    private val authRepository = AuthRepository(RetrofitClient.apiService)

}