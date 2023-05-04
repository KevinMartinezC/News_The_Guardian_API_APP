package com.example.news.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news.components.favorite.FavoriteScreen
import com.example.news.components.search.SearchScreen
import com.example.news.components.search.viewmodel.SearchViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    contentPadding: PaddingValues
) {
    val searchViewModel: SearchViewModel = getViewModel()
    val articles = searchViewModel.articlesFlow.collectAsLazyPagingItems()
    val isLoading = remember { mutableStateOf(false) }

    LaunchedEffect(articles.loadState.refresh) {
        isLoading.value = articles.loadState.refresh is LoadState.Loading
    }
    //val uiState by  searchViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Search.route
    ) {
        composable(route = BottomNavItem.Search.route) {
            SearchScreen(
                modifier = Modifier.padding(contentPadding),
                articles = articles,
                isLoading = isLoading.value,
                searchArticle = searchViewModel::searchArticles,
            )
        }
        composable(route = BottomNavItem.Favorite.route) {
            FavoriteScreen(modifier = Modifier.padding(contentPadding))
        }
    }
}

