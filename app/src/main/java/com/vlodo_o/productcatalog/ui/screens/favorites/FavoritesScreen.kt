package com.vlodo_o.productcatalog.ui.screens.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.vlodo_o.productcatalog.ui.components.EmptyContentView
import com.vlodo_o.productcatalog.ui.components.ErrorView
import com.vlodo_o.productcatalog.ui.components.LoaderView
import com.vlodo_o.productcatalog.ui.components.ProductGrid

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onProductClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is FavoritesUiState.Loading -> LoaderView()
        is FavoritesUiState.Error -> {
            val message = (state as FavoritesUiState.Error).message
            ErrorView(
                message = "Error: $message",
                onRetry = { viewModel.loadFavorites() }
            )
        }
        is FavoritesUiState.Empty -> {
            EmptyContentView()
        }
        is FavoritesUiState.Content -> {
            val products = (state as FavoritesUiState.Content).favorites
            ProductGrid(products, onProductClick)
        }
    }
}