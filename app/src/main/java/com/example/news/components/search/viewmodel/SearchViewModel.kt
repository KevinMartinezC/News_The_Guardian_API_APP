package com.example.news.components.search.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.news.components.search.SearchUiState
import com.example.news.model.network.Article
import com.example.news.model.network.Filter
import com.example.news.model.repository.GuardianRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class SearchViewModel(private val guardianRepository: GuardianRepository) : ViewModel() {

    private val _query = MutableStateFlow("")
    private val _filter = MutableStateFlow(Filter(""))

    val articlesFlow: Flow<PagingData<Article>> = combine(_query, _filter) { query, filter ->
        Pair(query, filter)
    }.flatMapLatest { (query, filter) ->
        guardianRepository.searchArticles(query, filter)
    }.cachedIn(viewModelScope)

    fun searchArticles(query: String, filter: Filter) {
        _query.value = query
        _filter.value = filter
    }
}
