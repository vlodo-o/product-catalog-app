package com.vlodo_o.productcatalog.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlodo_o.domain.model.LoadDataResult
import com.vlodo_o.domain.usecase.details.GetProductByIdUseCase
import com.vlodo_o.domain.usecase.favorites.AddToFavoritesUseCase
import com.vlodo_o.domain.usecase.favorites.IsFavoriteUseCase
import com.vlodo_o.domain.usecase.favorites.RemoveFromFavoritesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = ProductDetailsViewModel.Factory::class)
class ProductDetailsViewModel @AssistedInject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    @Assisted private val productId: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailsUiState>(ProductDetailsUiState.Loading)
    val uiState: StateFlow<ProductDetailsUiState> = _uiState.asStateFlow()

    init {
        loadProduct()
    }

    fun loadProduct() {
        viewModelScope.launch {
            _uiState.value = ProductDetailsUiState.Loading

            when (val result = getProductByIdUseCase(productId)) {
                is LoadDataResult.Success -> {
                    val product = result.data

                    val isFavorite = isFavoriteUseCase(product.id).getOrElse { false }

                    _uiState.value = ProductDetailsUiState.Content(
                        product = product,
                        isFavorite = isFavorite,
                        isOffline = result.isOffline
                    )
                }
                is LoadDataResult.Error -> {
                    _uiState.value = ProductDetailsUiState.Error(
                        message = "Something went wrong"
                    )
                }
            }
        }
    }

    fun toggleFavorite() {
        val currentState = _uiState.value
        if (currentState !is ProductDetailsUiState.Content) return

        val product = currentState.product
        val newFavoriteState = !currentState.isFavorite

        viewModelScope.launch {
            if (newFavoriteState) {
                addToFavoritesUseCase(product)
            } else {
                removeFromFavoritesUseCase(product)
            }

            _uiState.value = currentState.copy(isFavorite = newFavoriteState)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(productId: Int): ProductDetailsViewModel
    }
}