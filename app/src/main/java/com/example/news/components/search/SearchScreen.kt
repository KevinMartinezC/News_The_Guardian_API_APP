package com.example.news.components.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import com.example.news.R
import com.example.news.components.search.model.generateFilters
import com.example.news.data.network.Article
import com.example.news.data.network.Filter
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

    Column(modifier = modifier) {
        SearchField(
            query = query,
            onSearch = {
                coroutineScope.launch {
                    searchArticle(query.value, selectedFilter.value)
                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_8dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(stringResource(R.string.filters))
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.wight_8dp)))
            FilterDropdown(
                filters = filters,
                selectedFilter = selectedFilter,
                onFilterSelected = { filter ->
                    coroutineScope.launch { searchArticle(query.value, filter) }
                }
            )
        }
        ArticleList(articles = articles, isLoading = isLoading)
    }
}


