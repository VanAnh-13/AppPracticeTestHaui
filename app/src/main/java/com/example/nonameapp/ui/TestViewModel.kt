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

    private val _answer: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    val answer: LiveData<ArrayList<Int>> = _answer

    fun getQuestionsT(testId: String) {
        executeTask(
            request = {
                questionRepository.getTestById(testId)
            },
            onSuccess = { response ->
                Log.d("TestViewModel", "Questions: ${response.data}")
                val x = response.data.test
                _questionsT.postValue(x)
                val size = response.data.test.questions.size
                val initResult = List(size) { _ -> 0 }
                val test = arrayListOf<Int>()
                test.addAll(initResult)
                _answer.postValue(test)
            },
            onError = {
                Log.e("Error", "Error: ${it.message}")
                _error.postValue(it.message)
            }
        )
    }

    fun onAnswerItemClick(
        index: Int,
        selectAnswer: String,
    ) {
        val isCorrect = if(_questionsT.value?.questions?.getOrNull(index)?.correctAnswer?.equals(selectAnswer) == true) {
            1
        } else {
            0
        }
        val newList = ArrayList<Int>()

        _answer.value?.forEachIndexed { idx, value ->
            if (idx == index) {
                newList.add(isCorrect)
            } else {
                newList.add(value)
            }
        }

        _answer.postValue(newList)
    }
}