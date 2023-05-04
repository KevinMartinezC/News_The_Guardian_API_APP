package com.example.news.model.network


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class GuardianApiServiceImpl(private val client: HttpClient) : GuardianApiService {
    override suspend fun searchArticles(query: String): ApiResponse {
        val url =
            "https://content.guardianapis.com/search?q=$query&api-key=" +
                    "7de6160d-4534-4d15-9db6-dc0ea468d6e3&show-fields=thumbnail"
        return client.get(url).body()
    }
}

/*class GuardianApiServiceImpl(private val client: HttpClient) : GuardianApiService {
    override suspend fun searchArticles(query: String): ApiResponse {
        val url = "${BuildConfig.GUARDIAN_API_BASE_URL}/search?q=$query&api-key=${BuildConfig.GUARDIAN_API_KEY}"
        return client.get(url).body()
    }
}
*/