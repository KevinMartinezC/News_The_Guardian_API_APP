package com.example.news.components.search.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.components.search.SearchUiState
import com.example.news.model.network.Filter
import com.example.news.model.repository.GuardianRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val guardianRepository: GuardianRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SearchUiState(
            articles = emptyList(),
            isLoading = false
        )
    )
    val uiState = _uiState.asStateFlow()

    fun searchArticles(query: String, filter: Filter) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val response = guardianRepository.searchArticles(query, filter)
                Log.wtf("TEST","Response: ${response}")

                _uiState.value = SearchUiState(articles = response.response.results, isLoading = false)
            }catch (e: Exception){
                Log.wtf("TEST", "Exception: ${e::class.java.simpleName}")
                Log.wtf("TEST", "Message: ${e.message}")
                Log.wtf("TEST", "Stack trace: ${Log.getStackTraceString(e)}")
            }
        }
    }
}