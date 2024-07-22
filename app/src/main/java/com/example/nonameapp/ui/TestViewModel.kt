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


    private val _questions = MutableLiveData<List<QuestionsT>>()
    val questions: LiveData<List<QuestionsT>> get() = _questions

    private val _testName = MutableLiveData<String>()
    val testName: LiveData<String> get() = _testName

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getQuestionsT(testId: String) {
        executeTask(
            request = {
                questionRepository.getTestById(testId)
            },
            onSuccess = {
                _testName.postValue(it.data.name)
                _questions.postValue(it.data.questions)
                Log.d("TestViewModel", "getQuestionsT: ${it.data}")
            },
            onError = {
                _error.postValue(it.message)
                Log.e("TestViewModel", "getQuestionsT: ${it.message}")
            }
        )
    }
}