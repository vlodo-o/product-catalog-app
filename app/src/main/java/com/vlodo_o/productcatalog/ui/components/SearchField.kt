package com.vlodo_o.productcatalog.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var textState by remember { mutableStateOf(TextFieldValue(query, TextRange(query.length))) }

    OutlinedTextField(
        value = textState,
        onValueChange = { newValue ->
            textState = newValue
            onQueryChange(newValue.text)
        },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search")
        },
        trailingIcon = {
            if (textState.text.isNotEmpty()) {
                IconButton(onClick = {
                    textState = TextFieldValue("", TextRange(0))
                    onQueryChange("")
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Clear"
                    )
                }
            }
        },
        placeholder = { Text("Search products...") },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
    )

    LaunchedEffect(query) {
        if (query != textState.text) {
            textState = TextFieldValue(query, TextRange(query.length))
        }
    }
}