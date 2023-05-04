package com.example.news.data.network

import kotlinx.serialization.Serializable

@Serializable
data class Filter(
    val filterName: String,
    val section: String? = null,
    val tag: String? = null,
    val type: String? = null
)