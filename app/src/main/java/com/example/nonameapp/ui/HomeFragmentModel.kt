package com.example.nonameapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.model.SubjectResponse
import com.example.nonameapp.repository.SubjectRepository

private const val TAG = "HomeFragmentModel"

class HomeFragmentModel : BaseViewModel() {
    private val subjectRepository = SubjectRepository(apiService = RetrofitClient.apiService)
    private val _listSubject: MutableLiveData<SubjectResponse> by lazy { MutableLiveData<SubjectResponse>() }
    val listSubject = _listSubject

    fun getSubject(accessToken: String) = executeTask(
        request = {
            subjectRepository.getAllSubject(accessToken = accessToken)
        },
        onSuccess = {
            _listSubject.value = it.data
        },
        onError = {
            throw it
        },
    )
}