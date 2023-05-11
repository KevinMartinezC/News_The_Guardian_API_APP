package com.example.news.components.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.news.components.search.model.datastore.DataStoreProvider
import com.example.news.data.network.model.Article
import com.example.news.data.network.model.Filter
import com.example.news.components.search.model.repository.GuardianRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class SearchViewModel(
    private val guardianRepository: GuardianRepository,
    private val dataStoreProvider: DataStoreProvider,
) : ViewModel() {

    private val _query = MutableStateFlow("")
    private val _filter = MutableStateFlow(Filter(""))
    val selectedFilter: Flow<Filter> = dataStoreProvider.getSelectedFilter()

    @OptIn(ExperimentalCoroutinesApi::class)
    val articlesFlow: Flow<PagingData<Article>> = combine(_query, _filter) { query, filter ->
        Pair(query, filter)
    }.flatMapLatest { (query, filter) ->
        guardianRepository.searchArticles(query, filter)
    }.cachedIn(viewModelScope)

    fun searchArticles(query: String, filter: Filter) {//USE UPDATE INSTEAD THE VALUE
        _query.value = query
        _filter.value = filter
    }

    fun saveSelectedFilter(filter: Filter) {
        viewModelScope.launch {
            dataStoreProvider.saveSelectedFilter(filter)
        }
    }
}
