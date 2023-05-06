package com.example.news.components.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.news.DataStoreProvider
import com.example.news.components.favorite.UiState
import com.example.news.data.local.FavoriteArticle
import com.example.news.data.network.Article
import com.example.news.data.network.Filter
import com.example.news.data.repository.GuardianRepository
import com.example.news.data.repository.favorite.FavoriteArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val guardianRepository: GuardianRepository,
    private val dataStoreProvider: DataStoreProvider,
    private val favoriteArticlesRepository: FavoriteArticlesRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    private val _filter = MutableStateFlow(Filter(""))
    val selectedFilter: Flow<Filter> = dataStoreProvider.getSelectedFilter()

    private val _favoriteArticlesFlow = MutableStateFlow<List<FavoriteArticle>>(emptyList())
    val favoriteArticlesFlow = _favoriteArticlesFlow.asStateFlow()

    init {
        viewModelScope.launch {
            favoriteArticlesRepository.favoriteArticles
                .collect { favoriteArticles ->
                    val favoriteArticleId = favoriteArticles.map { it.id }.toSet()
                    _uiState.update { it.copy(favoriteArticle = favoriteArticleId) }
                    _favoriteArticlesFlow.value = favoriteArticles
                }
        }
    }


    private val _uiState = MutableStateFlow(
        UiState(
            favoriteArticle = emptySet()
        )
    )

    val uiState = _uiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val articlesFlow: Flow<PagingData<Article>> = combine(_query, _filter) { query, filter ->
        Pair(query, filter)
    }.flatMapLatest { (query, filter) ->
        guardianRepository.searchArticles(query, filter)
    }.cachedIn(viewModelScope)

    fun searchArticles(query: String, filter: Filter) {
        _query.value = query
        _filter.value = filter
    }

    fun saveSelectedFilter(filter: Filter) {
        viewModelScope.launch {
            dataStoreProvider.saveSelectedFilter(filter)
        }
    }

    fun addToFavorites(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            val favoriteArticle = FavoriteArticle(
                id = article.id,
                webTitle = article.webTitle,
                webUrl = article.webUrl,
                thumbnail = article.fields.thumbnail
            )
            if (_uiState.value.favoriteArticle.contains(article.id)) {
                favoriteArticlesRepository.removeFavoriteArticle(article.id)
            } else {
                favoriteArticlesRepository.addFavoriteArticle(favoriteArticle)
            }
        }
    }

    fun removeFromFavorites(articleId: String) {
        viewModelScope.launch {
            favoriteArticlesRepository.removeFavoriteArticle(articleId)
        }
    }
}
