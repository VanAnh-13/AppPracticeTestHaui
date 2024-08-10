package com.example.nonameapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.data.repository.QuestionRepository
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.model.QuestionsT
import com.example.nonameapp.response.Test

class TestViewModel : BaseViewModel() {
    private val questionRepository = QuestionRepository(RetrofitClient.apiService)


    private val _questionsT = MutableLiveData<Test>()
    val questionsT: LiveData<Test> get() = _questionsT

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getQuestionsT(testId: String) {
        executeTask(
            request = {
                questionRepository.getTestById(testId)
            },
            onSuccess = { response ->
                Log.d("TestViewModel", "Questions: ${response.data}")
                val x = response.data.test
                _questionsT.postValue(x)
            },
            onError = {
                Log.e("Error", "Error: ${it.message}")
                _error.postValue(it.message)
            }
        )
    }
}