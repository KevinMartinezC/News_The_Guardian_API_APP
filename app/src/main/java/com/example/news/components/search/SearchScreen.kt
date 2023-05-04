package com.example.news.components.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.news.R
import com.example.news.components.search.model.generateFilters
import com.example.news.model.network.Article
import com.example.news.model.network.Filter
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    isLoading: Boolean,
    searchArticle: (String, Filter) -> Unit,
) {

    val query = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val selectedFilter = remember { mutableStateOf(Filter("")) }
    val filters = remember { generateFilters() }
    val filtersMenuExpanded = remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        TextField(
            value = query.value,
            onValueChange = { newValue ->
                query.value = newValue
                coroutineScope.launch {
                    searchArticle(newValue, selectedFilter.value)
                }
            },
            label = { Text(stringResource(R.string.search)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                coroutineScope.launch {
                    searchArticle(query.value, selectedFilter.value)
                }
            }),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.filters))
            Spacer(modifier = Modifier.width(8.dp))
            Box {
                Button(onClick = { filtersMenuExpanded.value = !filtersMenuExpanded.value }) {
                    Text(selectedFilter.value.filterName.ifEmpty { stringResource(R.string.select_a_filter) })
                }
                DropdownMenu(
                    expanded = filtersMenuExpanded.value,
                    onDismissRequest = { filtersMenuExpanded.value = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    filters.forEach { filter ->
                        DropdownMenuItem(onClick = {
                            selectedFilter.value = filter
                            filtersMenuExpanded.value = false
                            coroutineScope.launch { searchArticle(query.value, filter) }

                        }, text = {
                            Text(text = filter.filterName)
                        })
                    }
                }
            }
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(articles) { article ->
                    article?.let {
                        NewsItem(article = it)
                    }
                }
                articles.apply {
                    if(loadState.append is LoadState.Loading){
                        item{
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentSize(Alignment.Center)
                                    .padding()
                            )
                        }
                    }
                }
            }


        }
    }
}