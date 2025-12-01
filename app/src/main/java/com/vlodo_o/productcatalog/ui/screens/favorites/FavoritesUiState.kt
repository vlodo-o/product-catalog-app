package com.vlodo_o.productcatalog.ui.screens.favorites

import com.vlodo_o.domain.model.Product

sealed class FavoritesUiState {
    object Loading : FavoritesUiState()
    data class Content(val favorites: List<Product>) : FavoritesUiState()
    data class Error(val message: String) : FavoritesUiState()
    object Empty : FavoritesUiState()
}