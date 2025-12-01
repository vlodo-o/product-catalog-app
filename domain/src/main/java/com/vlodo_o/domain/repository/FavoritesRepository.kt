package com.vlodo_o.domain.repository

import com.vlodo_o.domain.model.Product

interface FavoritesRepository {
    suspend fun getFavoriteProducts(): Result<List<Product>>
    suspend fun addToFavorites(product: Product): Result<Unit>
    suspend fun removeFromFavorites(product: Product): Result<Unit>
    suspend fun isFavorite(productId: Int): Result<Boolean>
}