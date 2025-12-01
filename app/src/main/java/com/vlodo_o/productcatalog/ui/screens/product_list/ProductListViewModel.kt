package com.vlodo_o.productcatalog.ui.screens.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlodo_o.domain.model.LoadDataResult
import com.vlodo_o.domain.model.ProductCategory
import com.vlodo_o.domain.usecase.product_list.GetCategoriesUseCase
import com.vlodo_o.domain.usecase.product_list.GetProductsByCategoryUseCase
import com.vlodo_o.domain.usecase.product_list.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductListUiState>(ProductListUiState.Loading)
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    private var allCategories: List<String> = emptyList()
    private var currentSearchQuery: String = ""
    private var currentCategory: ProductCategory? = null

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = ProductListUiState.Loading

            when (val productsResult = getProductsUseCase()) {
                is LoadDataResult.Success -> {
                    allCategories = listOf("all") + getCategoriesUseCase()
                    currentCategory = null

                    filterProducts(isOffline = productsResult.isOffline)
                }
                is LoadDataResult.Error -> {
                    _uiState.value = ProductListUiState.Error(
                        message = "Something went wrong"
                    )
                }
            }
        }
    }

    fun onCategorySelected(categoryName: String?) {
        currentCategory = if (categoryName == null || categoryName == "all") null
        else ProductCategory.fromDisplayName(categoryName)

        filterProducts()
    }

    fun onSearchQueryChanged(query: String) {
        currentSearchQuery = query
        filterProducts()
    }

    private fun filterProducts(isOffline: Boolean = false) {
        viewModelScope.launch {
            when (val categoryResult = getProductsByCategoryUseCase(currentCategory)) {
                is LoadDataResult.Success -> {
                    val filtered = categoryResult.data.filter { product ->
                        product.title.contains(currentSearchQuery, ignoreCase = true)
                    }

                    _uiState.value = ProductListUiState.Content(
                        products = filtered,
                        categories = allCategories,
                        selectedCategory = currentCategory?.displayName ?: "all",
                        searchQuery = currentSearchQuery,
                        isOffline = categoryResult.isOffline || isOffline
                    )
                }
                is LoadDataResult.Error -> {
                    _uiState.value = ProductListUiState.Error(
                        message = "Something went wrong"
                    )
                }
            }
        }
    }

    fun retry() {
        loadData()
    }
}