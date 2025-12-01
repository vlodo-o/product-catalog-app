package com.vlodo_o.domain.usecase.favorites

import com.vlodo_o.domain.repository.FavoritesRepository

class IsFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(productId: Int) = repository.isFavorite(productId)
}