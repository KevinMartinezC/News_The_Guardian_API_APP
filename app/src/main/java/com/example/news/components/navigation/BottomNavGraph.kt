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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news.components.detail.DetailScreen
import com.example.news.components.favorite.FavoriteScreen
import com.example.news.components.favorite.viewmodel.FavoritesViewModel
import com.example.news.components.search.SearchScreen
import com.example.news.components.search.viewmodel.SearchViewModel
import org.koin.androidx.compose.getViewModel

private const val NAV_ARGUMENT = "url"
private const val ROUTE_DETAIL = "detail/{url}"

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    contentPadding: PaddingValues
) {
    val searchViewModel: SearchViewModel = getViewModel()
    val favoritesViewModel: FavoritesViewModel = getViewModel()
    val articles = searchViewModel.articlesFlow.collectAsLazyPagingItems()
    val isLoading = remember { mutableStateOf(false) }
    val saveSelectedFilter = searchViewModel::saveSelectedFilter
    val selectedFilter = searchViewModel.selectedFilter
    val uiState by favoritesViewModel.uiState.collectAsState()

    LaunchedEffect(articles.loadState.refresh) {
        isLoading.value = articles.loadState.refresh is LoadState.Loading
    }

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
                saveSelectedFilter = saveSelectedFilter,
                selectedFilter = selectedFilter,
                navController = navController,
                uiState = uiState,
                onToggleFavorite = { article -> favoritesViewModel.addToFavorites(article) }
            )
        }

        composable(route = BottomNavItem.Favorite.route) {
            FavoriteScreen(
                favoriteArticlesFlow = favoritesViewModel.favoriteArticlesFlow,
                removeFromFavorites = favoritesViewModel::removeFromFavorites,
                navController = navController,

                )
        }

        composable(
            route = ROUTE_DETAIL,
            arguments = listOf(navArgument(NAV_ARGUMENT) { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString(NAV_ARGUMENT).orEmpty()
            DetailScreen(url = url, modifier = Modifier.padding(contentPadding))
        }
    }
}

