package com.example.news.components.favorite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.moviesvapp.ui.theme.MyApplicationTheme
import com.example.news.components.favorite.FavoriteScreenConstants.CARD_HEIGHT_FACTOR
import com.example.news.components.favorite.FavoriteScreenConstants.CARD_WIDTH_FACTOR
import com.example.news.components.favorite.FavoriteScreenConstants.PAGER_SNAP_DISTANCE
import com.example.news.data.local.FavoriteArticle
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(
    favoriteArticlesFlow: StateFlow<List<FavoriteArticle>>,
    removeFromFavorites: (String) -> Unit

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
        val cardWidth = screenWidth * CARD_WIDTH_FACTOR
        val cardHeight = screenHeight * CARD_HEIGHT_FACTOR
        val padding = (screenWidth - cardWidth) / 2

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            HorizontalPager(
                pageCount = favoriteArticles.size,
                state = pagerState,
                flingBehavior = fling,
                contentPadding = PaddingValues(start = padding, end = padding)
            ) { page ->
                val favoriteArticle = favoriteArticles[page]
                FavoriteArticleItem(
                    favoriteArticle = favoriteArticle,
                    pagerState = pagerState,
                    currentPage = page,
                    cardWidth = cardWidth,
                    cardHeight = cardHeight,
                    removeFromFavorites = { article -> removeFromFavorites(article.id) }

                )
            }
        }
    }
}

object FavoriteScreenConstants {
    const val CARD_WIDTH_FACTOR = 0.7f
    const val CARD_HEIGHT_FACTOR = 0.7f
    const val PAGER_SNAP_DISTANCE = 4
}

