package com.example.news.components.favorite

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.news.data.local.FavoriteArticle
import kotlin.math.absoluteValue


private const val SCALE_START = 0.8f
private const val SCALE_STOP = 1f
private const val PAGE_OFFSET_LOWER_BOUND = 0f
private const val PAGE_OFFSET_UPPER_BOUND = 1f
private const val INITIAL_FRACTION_VALUE = 1f
private const val LERP_START_WEIGHT = 1
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteArticleItem(
    favoriteArticle: FavoriteArticle,
    pagerState: PagerState,
    currentPage: Int,
    cardWidth: Dp,
    cardHeight: Dp,
    removeFromFavorites: (FavoriteArticle) -> Unit
) {
    val painter = rememberAsyncImagePainter(model = favoriteArticle.thumbnail)
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }

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
        modifier = Modifier
            .size(cardWidth, cardHeight)
            .scale(scale)
            .animateContentSize()
            .clickable(onClick = {
                openBottomSheet = !openBottomSheet
            }),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painter,
                contentDescription = "Article Poster",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { removeFromFavorites(favoriteArticle) },
                modifier = Modifier
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height) {
                            placeable.place(
                                x = constraints.maxWidth - placeable.width - 8.dp.roundToPx(),
                                y = 8.dp.roundToPx()
                            )
                        }
                    }
                    .padding(8.dp)
                    .background(color = Color.Black.copy(alpha = 0.5f), shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove from favorites",
                    tint = Color.White
                )
            }
        }
    }
}

fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (LERP_START_WEIGHT - fraction) * start + fraction * stop
}
