package com.example.news.components.favorite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.news.R
import com.example.news.theme.MyApplicationTheme
import com.example.news.components.favorite.model.local.FavoriteArticle
import com.example.news.components.favorite.utils.isLandscape
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

const val CARD_WIDTH_FACTOR = 0.7f
const val CARD_HEIGHT_FACTOR = 0.7f
const val CARD_WIDTH_FACTOR_LANDSCAPE = 0.4f
const val CARD_HEIGHT_FACTOR_LANDSCAPE = 0.5f
const val PAGER_SNAP_DISTANCE = 4

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(
    favoriteArticlesFlow: StateFlow<List<FavoriteArticle>>,
    removeFromFavorites: (String) -> Unit,
    navController: NavHostController
) {
    val favoriteArticles by favoriteArticlesFlow.collectAsState(initial = emptyList())

    MyApplicationTheme {
        val pagerState = rememberPagerState()
        val fling = PagerDefaults.flingBehavior(
            state = pagerState,
            pagerSnapDistance = PagerSnapDistance.atMost(PAGER_SNAP_DISTANCE)
        )

        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val isLandscape = isLandscape()
        val cardWidthFactor = if (isLandscape) CARD_WIDTH_FACTOR_LANDSCAPE else CARD_WIDTH_FACTOR
        val cardHeightFactor = if (isLandscape) CARD_HEIGHT_FACTOR_LANDSCAPE else CARD_HEIGHT_FACTOR
        val cardWidth = screenWidth * cardWidthFactor
        val cardHeight = screenHeight * cardHeightFactor
        val padding = (screenWidth - cardWidth) / 2

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            HorizontalPager(
                pageCount = if (favoriteArticles.isEmpty()) 1 else favoriteArticles.size,
                state = pagerState,
                flingBehavior = fling,
                contentPadding = PaddingValues(start = padding, end = padding)
            ) { page ->
                if (favoriteArticles.isEmpty()) {
                    Text(
                        text = stringResource(R.string.no_favorites_added_yet),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                } else {
                    val favoriteArticle = favoriteArticles[page]
                    FavoriteArticleItem(
                        favoriteArticle = favoriteArticle,
                        pagerState = pagerState,
                        currentPage = page,
                        modifier = Modifier.size(cardWidth, cardHeight),
                        removeFromFavorites = { article -> removeFromFavorites(article.id) },
                        navController = navController
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun FavoriteScreenPreview() {
    val dummyFavoriteArticles = listOf(
        FavoriteArticle(
            id = "1",
            webTitle = "Article 1",
            thumbnail = "https://via.placeholder.com/150",
            webUrl = "https://www.example.com/article1"
        ),
        FavoriteArticle(
            id = "2",
            webTitle = "Article 2",
            thumbnail = "https://via.placeholder.com/150",
            webUrl = "https://www.example.com/article2"
        ),
        FavoriteArticle(
            id = "3",
            webTitle = "Article 3",
            thumbnail = "https://via.placeholder.com/150",
            webUrl = "https://www.example.com/article3"
        )
    )
    val favoriteArticlesFlow = MutableStateFlow(dummyFavoriteArticles)

    FavoriteScreen(
        favoriteArticlesFlow = favoriteArticlesFlow,
        removeFromFavorites = {  },
        navController = rememberNavController()
    )
}