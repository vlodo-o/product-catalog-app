package com.vlodo_o.domain.usecase.favorites

import com.vlodo_o.domain.model.Product
import com.vlodo_o.domain.repository.FavoritesRepository

class AddToFavoritesUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(product: Product) = repository.addToFavorites(product)
}