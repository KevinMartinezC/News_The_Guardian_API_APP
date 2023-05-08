package com.example.news.components.favorite.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

const val HORIZONTAL_PADDING_LANDSCAPE = 0.3f
const val HORIZONTAL_PADDING_PORTRAIT = 0.15f
const val VERTICAL_PADDING_LANDSCAPE = 0.25f
const val VERTICAL_PADDING_PORTRAIT = 0.15f

@Composable
fun isLandscape(): Boolean {
    val orientation = LocalConfiguration.current.orientation
    return orientation == Configuration.ORIENTATION_LANDSCAPE
}

fun getHorizontalPadding(isLandscape: Boolean): Float =
    if (isLandscape) HORIZONTAL_PADDING_LANDSCAPE else HORIZONTAL_PADDING_PORTRAIT

fun getVerticalPadding(isLandscape: Boolean): Float =
    if (isLandscape) VERTICAL_PADDING_LANDSCAPE else VERTICAL_PADDING_PORTRAIT
