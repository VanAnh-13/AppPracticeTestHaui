package com.example.nonameapp.base

import com.example.nonameapp.model.ApiResponse
import com.example.nonameapp.model.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseRepository {
    suspend fun <T> getResult(
        request: suspend CoroutineScope.() -> T
    ): DataState<T> {
        return withContext(Dispatchers.IO) {
            try {
                DataState.Success(request())
            } catch (exception: Exception) {
                DataState.Error(exception)
            }
        }
    }
}