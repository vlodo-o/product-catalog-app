package com.vlodo_o.productcatalog.ui.screens.product_list

import com.vlodo_o.domain.model.Product

sealed class ProductListUiState {
    object Loading : ProductListUiState()
    data class Content(
        val products: List<Product>,
        val categories: List<String>,
        val selectedCategory: String?,
        val searchQuery: String,
        val isOffline: Boolean = false
    ) : ProductListUiState()
    data class Error(val message: String) : ProductListUiState()
}