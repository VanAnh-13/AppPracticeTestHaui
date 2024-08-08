package com.example.nonameapp.ui

import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.data.repository.SearchRepository
import com.example.nonameapp.data.source.network.RetrofitClient
import com.example.nonameapp.response.SearchResponse

class SearchViewModel : BaseViewModel() {
    private val searchRepository = SearchRepository(RetrofitClient.apiService)
    fun search(
        query: String,
        onSearchSuccess: (SearchResponse) -> Unit,
        onSearchError: (Exception) -> Unit
    ) {
        executeTask(
            request = {
                searchRepository.searchQuestions(query)
            },
            onSuccess = {
                onSearchSuccess.invoke(it.data)
            },
            onError = {
                onSearchError.invoke(it)
            }
        )
    }
}