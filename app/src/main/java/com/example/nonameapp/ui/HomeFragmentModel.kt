package com.example.nonameapp.ui

import androidx.lifecycle.MutableLiveData
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.data.repository.SubjectRepository
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.response.SubjectResponse

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