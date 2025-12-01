package com.vlodo_o.productcatalog.ui.screens.product_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vlodo_o.productcatalog.ui.components.CategoryChipsRow
import com.vlodo_o.productcatalog.ui.components.EmptyContentView
import com.vlodo_o.productcatalog.ui.components.ErrorView
import com.vlodo_o.productcatalog.ui.components.LoaderView
import com.vlodo_o.productcatalog.ui.components.SearchField
import androidx.hilt.navigation.compose.hiltViewModel
import com.vlodo_o.productcatalog.ui.components.ProductGrid

@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = hiltViewModel(),
    onProductClick: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        val contentState = state as? ProductListUiState.Content

        SearchField(
            query = contentState?.searchQuery.orEmpty(),
            onQueryChange = viewModel::onSearchQueryChanged
        )

        CategoryChipsRow(
            categories = contentState?.categories.orEmpty(),
            selectedCategory = contentState?.selectedCategory,
            onCategorySelected = viewModel::onCategorySelected
        )
        if (contentState?.isOffline == true) {
            Surface(
                color = MaterialTheme.colorScheme.errorContainer,
                tonalElevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "You are currently offline, showing cached data",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
        when (state) {
            is ProductListUiState.Loading -> LoaderView()
            is ProductListUiState.Error -> {
                val message = (state as ProductListUiState.Error).message
                ErrorView(
                    message = "Error: $message",
                    onRetry = viewModel::retry
                )
            }
            is ProductListUiState.Content -> {
                val content = state as ProductListUiState.Content
                if (content.products.isEmpty()) {
                    EmptyContentView()
                } else {
                    ProductGrid(content.products, onProductClick)
                }
            }
        }
    }
}
