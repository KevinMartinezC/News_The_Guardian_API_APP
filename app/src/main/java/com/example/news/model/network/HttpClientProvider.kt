package com.example.news.model.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientProvider {
    fun createClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout){
                requestTimeoutMillis = 60_000L
                connectTimeoutMillis = 60_000L
                socketTimeoutMillis = 60_000L
            }
           /* install(DefaultRequest) {
                host = "content.guardianapis.com"
                url {
                    protocol = URLProtocol.HTTPS
                }
                contentType(ContentType.Application.Json)
            }*/
        }
    }
}