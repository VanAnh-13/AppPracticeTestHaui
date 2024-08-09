package com.example.nonameapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.data.repository.QuestionRepository
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.model.QuestionsT

class TestViewModel: BaseViewModel() {
    private val questionRepository = QuestionRepository(RetrofitClient.apiService)


    private val _questionsT = MutableLiveData<List<QuestionsT>>()
    val questionsT: LiveData<List<QuestionsT>> get() = _questionsT

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getQuestionsT(testId: String) {
        executeTask(
            request = {
                questionRepository.getTestById(testId)
            },
            onSuccess = {response ->
                Log.d("TestViewModel", "API response: $response")
                Log.d("TestViewModel", "Questions: ${response.data.questionsT}")
                _questionsT.postValue(response.data.questionsT)
            },
            onError = {
                Log.e("TestViewModel", "Error: ${it.message}")
                _error.postValue(it.message)
            }
        )
    }
}