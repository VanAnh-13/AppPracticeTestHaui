package com.example.nonameapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.data.repository.QuestionRepository
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.model.Question

class QuestionsViewModel : BaseViewModel() {
    private val questionRepository = QuestionRepository(RetrofitClient.apiService)

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> get() = _questions

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    fun getQuestions(accessToken: String, subjectId: String) {
        executeTask(
            request = {
                questionRepository.getQuestions(accessToken, subjectId)
            },
            onSuccess = {
                _questions.postValue(it.data.questions)
                Log.d("QuestionsViewModel", "getQuestions: ${it.data}")
            },
            onError = {
                _error.postValue(it.message)
                Log.e("QuestionsViewModel", "getQuestions: ${it.message}")
            }
        )
    }
}