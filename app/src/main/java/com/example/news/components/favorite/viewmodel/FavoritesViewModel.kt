package com.example.news.components.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.components.favorite.UiState
import com.example.news.components.favorite.model.local.FavoriteArticle
import com.example.news.data.network.model.Article
import com.example.news.components.favorite.model.repository.FavoriteArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoriteArticlesRepository: FavoriteArticlesRepository
) : ViewModel() {

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

    fun addToFavorites(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            val favoriteArticle = FavoriteArticle(
                id = article.id,
                webTitle = article.webTitle,
                webUrl = article.webUrl,
                thumbnail = article.fields.thumbnail
            )
            favoriteArticlesRepository.addFavoriteArticle(favoriteArticle)
        }
    }

    fun removeFromFavorites(articleId: String) {
        viewModelScope.launch {
            favoriteArticlesRepository.removeFavoriteArticle(articleId)
        }
    }
}
