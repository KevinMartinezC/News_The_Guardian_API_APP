package com.example.news.model


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class GuardianApiServiceImpl : GuardianApiService {
    private val client: HttpClient by lazy { createClient()}

    private fun createClient(): HttpClient{
        return HttpClient(OkHttp){
        install(DefaultRequest){
            host = "BASE_URL"
            url{
                protocol = URLProtocol.HTTPS
            }
            contentType(ContentType.Application.Json)
           }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    override suspend fun searchArticles(query: String): ApiResponse {
        val url = "https://content.guardianapis.com/search?q=$query&api-key=7de6160d-4534-4d15-9db6-dc0ea468d6e3"
       return client.get(url).body()
    }
}


suspend fun testSearchArticles() {
    val guardianApiService = GuardianApiServiceImpl()
    val query = "technology"
    val response = guardianApiService.searchArticles(query)

    println("Status: ${response.response.status}")
    println("Total Results: ${response.response.results}")
    println("Results:")
    response.response.results.forEach { article ->
        println("Title: ${article.webTitle}")
        println("URL: ${article.webUrl}")
        println()
    }
}
