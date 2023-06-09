package com.example.news.data.network

import com.example.news.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
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
                requestTimeoutMillis = 60_000L //mOVE IT IN A CONSTANT
                connectTimeoutMillis = 60_000L
                socketTimeoutMillis = 60_000L
            }
            install(DefaultRequest) {
                host = BuildConfig.GUARDIAN_API_BASE_URL
                url {
                    protocol = URLProtocol.HTTPS
                    parameters.append(API_KEY_NAME, BuildConfig.GUARDIAN_API_KEY)
                }
                contentType(ContentType.Application.Json)
            }
        }
    }

    companion object{
        const val API_KEY_NAME = "api-key"
    }
}