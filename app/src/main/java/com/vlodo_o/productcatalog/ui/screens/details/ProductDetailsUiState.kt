package com.vlodo_o.productcatalog.ui.screens.details

import com.vlodo_o.domain.model.Product

sealed class ProductDetailsUiState {
    object Loading : ProductDetailsUiState()
    data class Content(
        val product: Product,
        val isFavorite: Boolean,
        val isOffline: Boolean = false
    ) : ProductDetailsUiState()
    data class Error(val message: String) : ProductDetailsUiState()
}