package com.example.news.components.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.news.R
import com.example.news.data.network.Filter


@Composable
fun FilterDropdown(
    filters: List<Filter>,
    selectedFilter: Filter,
    onFilterSelected: (Filter) -> Unit
) {
    val filtersMenuExpanded = remember { mutableStateOf(false) }

    Box {
        Button(onClick = { filtersMenuExpanded.value = !filtersMenuExpanded.value }) {
            Text(selectedFilter.filterName.ifEmpty { stringResource(R.string.select_a_filter) })
        }
        DropdownMenu(
            expanded = filtersMenuExpanded.value,
            onDismissRequest = { filtersMenuExpanded.value = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            filters.forEach { filter ->
                DropdownMenuItem(onClick = {
                    filtersMenuExpanded.value = false
                    onFilterSelected(filter)
                }, text = {
                    Text(text = filter.filterName)
                })
            }
        }
    }
}

@Preview
@Composable
fun FilterDropdownPreview() {
    val filters = listOf(Filter("Filter 1"), Filter("Filter 2"), Filter("Filter 3"))
    val selectedFilter = Filter("Filter 1")
    val onFilterSelected: (Filter) -> Unit = {}
    FilterDropdown(filters, selectedFilter, onFilterSelected)
}