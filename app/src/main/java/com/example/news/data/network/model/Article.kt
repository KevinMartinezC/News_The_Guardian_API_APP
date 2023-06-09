package com.example.news.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("id")
    val id: String,

    @SerialName("type")
    val type: String,

    @SerialName("sectionId")
    val sectionId: String,

    @SerialName("sectionName")
    val sectionName: String,

    @SerialName("webPublicationDate")
    val webPublicationDate: String,

    @SerialName("webTitle")
    val webTitle: String,

    @SerialName("webUrl")
    val webUrl: String,

    @SerialName("apiUrl")
    val apiUrl: String,

    @SerialName("fields")
    val fields: Fields
)

@Serializable
data class Fields(
    @SerialName("thumbnail")
    val thumbnail: String?
)

