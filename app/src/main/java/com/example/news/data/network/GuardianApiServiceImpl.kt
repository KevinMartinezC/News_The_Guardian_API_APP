package com.example.news.data.network


import com.example.news.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GuardianApiServiceImpl(private val client: HttpClient) : GuardianApiService {
    override suspend fun searchArticles(query: String, filter: Filter, page: Int, pageSize: Int): ApiResponse {

        return client.get("https://content.guardianapis.com/search") {
            parameter("q", query)
            addCommonParameters(BuildConfig.GUARDIAN_API_KEY, pageSize)
            parameter("page", page)
            parameter("show-fields", "thumbnail")
            filter.section?.let { parameter("section", it) }
            filter.tag?.let { parameter("tag", it) }
            filter.type?.let { parameter("type", it) }
        }.body()
    }
}

fun HttpRequestBuilder.addCommonParameters(apiKey: String, pageSize: Int) {
    parameter("api-key", apiKey)
    parameter("page-size", pageSize)
}

