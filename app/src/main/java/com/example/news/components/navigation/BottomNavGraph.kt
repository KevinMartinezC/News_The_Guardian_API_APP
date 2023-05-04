package com.example.news.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    val uiState by  searchViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Search.route
    ) {
        composable(route = BottomNavItem.Search.route) {
            SearchScreen(
                modifier = Modifier.padding(contentPadding),
                uiState = uiState,
                searchArticle = searchViewModel::searchArticles,
            )
        }
        composable(route = BottomNavItem.Favorite.route) {
            FavoriteScreen(modifier = Modifier.padding(contentPadding))
        }
    }
}

