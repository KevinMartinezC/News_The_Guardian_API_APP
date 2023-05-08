package com.example.news.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Filter(
    @SerialName("filterName")
    val filterName: String,

    @SerialName("section")
    val section: String? = null,

    @SerialName("tag")
    val tag: String? = null,

    @SerialName("type")
    val type: String? = null
)