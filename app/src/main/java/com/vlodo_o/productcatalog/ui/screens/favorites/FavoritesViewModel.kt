package com.vlodo_o.productcatalog.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlodo_o.domain.usecase.favorites.GetFavoriteProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<FavoritesUiState>(FavoritesUiState.Loading)
    val state: StateFlow<FavoritesUiState> = _state.asStateFlow()

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _state.value = FavoritesUiState.Loading

            val result = getFavoriteProductsUseCase()

            _state.value = result.fold(
                onSuccess = { products ->
                    if (products.isEmpty()) {
                        FavoritesUiState.Empty
                    } else {
                        FavoritesUiState.Content(products)
                    }
                },
                onFailure = { error ->
                    FavoritesUiState.Error(error.message ?: "")
                }
            )
        }
    }
}