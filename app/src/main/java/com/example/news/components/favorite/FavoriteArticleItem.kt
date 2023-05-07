package com.example.news.components.favorite

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.moviesvapp.ui.theme.MyApplicationTheme
import com.example.news.R
import com.example.news.components.favorite.model.local.FavoriteArticle
import java.net.URLEncoder
import kotlin.math.absoluteValue


private const val SCALE_START = 0.8f
private const val SCALE_STOP = 1f
private const val PAGE_OFFSET_LOWER_BOUND = 0f
private const val PAGE_OFFSET_UPPER_BOUND = 1f
private const val INITIAL_FRACTION_VALUE = 1f
private const val LERP_START_WEIGHT = 1
private const val TRANSPARENT_BLACK = 0.5f

fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (LERP_START_WEIGHT - fraction) * start + fraction * stop
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteArticleItem(
    favoriteArticle: FavoriteArticle,
    pagerState: PagerState,
    currentPage: Int,
    modifier: Modifier = Modifier,
    removeFromFavorites: (FavoriteArticle) -> Unit,
    navController: NavController
) {
    val painter = rememberAsyncImagePainter(model = favoriteArticle.thumbnail)

    val pageOffset = (
            (pagerState.currentPage - currentPage) + pagerState.currentPageOffsetFraction
            ).absoluteValue

    val scale = lerp(
        start = SCALE_START,
        stop = SCALE_STOP,
        fraction = INITIAL_FRACTION_VALUE - pageOffset.coerceIn(
            PAGE_OFFSET_LOWER_BOUND,
            PAGE_OFFSET_UPPER_BOUND
        )
    )

    Card(
        modifier = modifier
            .scale(scale)
            .animateContentSize()
            .clickable(onClick = {
                navController.navigate(
                    "detail/${
                        URLEncoder.encode(
                            favoriteArticle.webUrl,
                            "UTF-8"
                        )
                    }"
                )
            }
            ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painter,
                contentDescription = stringResource(R.string.article_poster),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            val spacing = dimensionResource(id = R.dimen.spacing_8dp)

            IconButton(
                onClick = { removeFromFavorites(favoriteArticle) },
                modifier = Modifier
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height) {
                            placeable.place(

                                x = constraints.maxWidth - placeable.width - spacing.roundToPx(),
                                y = spacing.roundToPx()
                            )
                        }
                    }
                    .padding(dimensionResource(id = R.dimen.padding_8dp))
                    .background(
                        color = Color.Black.copy(alpha = TRANSPARENT_BLACK),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.remove_from_favorites),
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalFoundationApi::class)
fun FavoriteArticleItemPreview() {
    MyApplicationTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val sampleFavoriteArticle = FavoriteArticle(
                id ="example",
                webTitle = "Sample Article",
                thumbnail = "https://example.com/sample-thumbnail1.jpg",
                webUrl = "https://example.com/sample-article"
            )

            val pagerState = rememberPagerState()
            val navController = rememberNavController()

            FavoriteArticleItem(
                favoriteArticle = sampleFavoriteArticle,
                pagerState = pagerState,
                currentPage = 0,
                removeFromFavorites = { /* Do nothing in preview */ },
                navController = navController
            )
        }
    }
}