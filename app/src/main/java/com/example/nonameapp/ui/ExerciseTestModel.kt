package com.example.nonameapp.ui

import androidx.lifecycle.MutableLiveData
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.data.repository.TestRepository
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.response.SubjectResponse
import com.example.nonameapp.response.TestResponse

class ExerciseTestModel : BaseViewModel() {
    private val testRepository = TestRepository(apiService = RetrofitClient.apiService)
    private val _listSubject by lazy { MutableLiveData<TestResponse>() }
    val listSubject = _listSubject

    fun getSubject(subjectId: String) = executeTask(
        request = {
            testRepository.getTest(subjectId)
        },
        onSuccess = { response ->
            _listSubject.value = response.data
        },
        onError = {
            throw it
        },
    )
}