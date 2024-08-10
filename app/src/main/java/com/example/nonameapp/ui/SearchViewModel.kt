package com.example.nonameapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.base.DataState
import com.example.nonameapp.data.repository.SearchRepository
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.model.Question
import com.example.nonameapp.request.CreatePostRequest
import com.example.nonameapp.response.SearchResponse
import kotlinx.coroutines.launch

class SearchViewModel : BaseViewModel() {
    private val searchRepository = SearchRepository(RetrofitClient.apiService)

    private val _searchResults = MutableLiveData<SearchResponse>()
    val searchResults: LiveData<SearchResponse> get() = _searchResults

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun search(accessToken: String, query: String) {
        executeTask(
            request = {
                searchRepository.searchQuestions(accessToken, query)
            },
            onSuccess = {
                updateSearchResults(it.data)
            },
            onError = {
                it.message?.let { it1 -> setErrorMessage(it1) }
            }
        )
    }


    private fun updateSearchResults(results: SearchResponse) {
        _searchResults.value = results
    }

    private fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }

    fun createPost(
        accessToken: String,
        createPostRequest: CreatePostRequest,
        onCreateSuccess: (String) -> Unit,
        onCreateError: () -> Unit
    ) = executeTask(
        request = {
            searchRepository.createPost(token = accessToken, createPostRequest = createPostRequest)
        },
        onSuccess = {
            onCreateSuccess.invoke(it.message)
        },
        onError = {
            onCreateError.invoke()
        }
    )
}

