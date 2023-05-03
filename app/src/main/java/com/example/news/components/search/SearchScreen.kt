package com.example.news.components.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.news.model.Article
import com.example.news.model.GuardianRepository
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    val guardianRepository: GuardianRepository = get()
    val query = remember { mutableStateOf("") }
    val articles = remember { mutableStateOf(emptyList<Article>()) }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        TextField(
            value = query.value,
            onValueChange = { newValue -> query.value = newValue },
            label = { Text("Search") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                coroutineScope.launch {
                    val response = guardianRepository.searchArticles(query.value)
                    articles.value = response.response.results
                }
            }),
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(articles.value) { article ->
                Text(text = article.webTitle, modifier = Modifier.padding(8.dp))
            }
        }
    }
}
