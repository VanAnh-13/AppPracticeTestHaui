package com.example.nonameapp.base

import java.lang.Exception

sealed class DataState<out T> {

    data class Success<T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception): DataState<Nothing>()
}