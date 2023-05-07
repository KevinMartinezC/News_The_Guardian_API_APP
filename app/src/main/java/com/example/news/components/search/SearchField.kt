package com.example.news.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.news.R
import kotlinx.coroutines.launch

private const val UNFOCUS_BORDER_COLOR = 0.5f

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(
    query: MutableState<String>, onSearch: () -> Unit
) {
    val keyBoardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()


    OutlinedTextField(
        value = query.value,
        onValueChange = { newValue -> query.value = newValue },
        label = { Text(stringResource(R.string.search)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            scope.launch {
                onSearch()
                keyBoardController?.hide()
            }
        }),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_8dp)),
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface
                .copy(alpha = UNFOCUS_BORDER_COLOR),
        ),
        trailingIcon = {
            IconButton(onClick = {
                scope.launch {
                    onSearch()
                    keyBoardController?.hide()
                }
            }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@Preview
@Composable
fun SearchFieldPreview() {
    val query = remember { mutableStateOf("") }
    val onSearch = {}

    SearchField(query, onSearch)
}

