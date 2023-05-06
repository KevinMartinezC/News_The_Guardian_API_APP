package com.example.news.components.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.news.components.favorite.UiState
import com.example.news.data.network.Article


@Composable
fun ArticleList(
    uiState: UiState,
    onToggleFavorite: (Article) -> Unit,
    articles: LazyPagingItems<Article>,
    isLoading: Boolean,
    navController: NavHostController
) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(articles) { article ->
                article?.let {
                    NewsItem(
                        article = it,
                        navController = navController,
                        favoriteArticle = uiState.favoriteArticle,
                        onToggleFavorite = onToggleFavorite
                    )
                }
            }
            articles.apply {
                if (loadState.append is LoadState.Loading) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                                .padding()
                        )
                    }
                }
            }
        }
    }
}
