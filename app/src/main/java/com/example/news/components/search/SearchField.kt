package com.example.news.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.example.news.R

@Composable
fun SearchField(
    query: MutableState<String>,
    onSearch: () -> Unit
) {
    OutlinedTextField(
        value = query.value,
        onValueChange = { newValue ->
            query.value = newValue
        },
        label = { Text(stringResource(R.string.search)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { onSearch() }),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_8dp)),
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        )
    )
}