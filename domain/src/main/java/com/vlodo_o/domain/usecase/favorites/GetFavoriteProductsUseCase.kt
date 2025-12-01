package com.vlodo_o.domain.usecase.favorites

import com.vlodo_o.domain.repository.FavoritesRepository

class GetFavoriteProductsUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke() = repository.getFavoriteProducts()
}