package com.example.news.data.network


import com.example.news.data.network.GuardianApiServiceImpl.Companion.PAGE_SIZE
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GuardianApiServiceImpl(private val client: HttpClient) : GuardianApiService {
    override suspend fun searchArticles(
        query: String,
        filter: Filter,
        page: Int,
        pageSize: Int
    ): ApiResponse {

        return client.get(URL_STRING) {
            parameter(QUERY, query)
            addCommonParameters(pageSize)
            parameter(PAGE, page)
            parameter(SHOW_FIELDS, THUMBNAIL)
            filter.section?.let { parameter(SECTION, it) }
            filter.tag?.let { parameter(TAG, it) }
            filter.type?.let { parameter(TYPE, it) }
        }.body()
    }

    companion object {
        const val URL_STRING = "search"
        const val QUERY = "q"
        const val PAGE = "page"
        const val SHOW_FIELDS = "show-fields"
        const val THUMBNAIL = "thumbnail"
        const val SECTION = "section"
        const val TAG = "tag"
        const val TYPE = "type"
        const val PAGE_SIZE = "page-size"
    }
}

fun HttpRequestBuilder.addCommonParameters(pageSize: Int) {
    parameter(PAGE_SIZE, pageSize)
}

