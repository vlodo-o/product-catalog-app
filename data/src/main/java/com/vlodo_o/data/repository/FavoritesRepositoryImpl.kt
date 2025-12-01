package com.vlodo_o.data.repository

import com.vlodo_o.data.local.dao.FavoriteProductDao
import com.vlodo_o.data.local.mapper.toDomain
import com.vlodo_o.data.local.mapper.toFavoriteEntity
import com.vlodo_o.domain.model.Product
import com.vlodo_o.domain.repository.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteProductDao,
) : FavoritesRepository {

    override suspend fun getFavoriteProducts(): Result<List<Product>> {
        return try {
            val entities = favoriteDao.getAllFavorites()
            val products = entities.map { it.toDomain() }
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addToFavorites(product: Product): Result<Unit> {
        return try {
            val entity = product.toFavoriteEntity()
            favoriteDao.addFavorite(entity)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun removeFromFavorites(product: Product): Result<Unit> {
        return try {
            favoriteDao.removeFavorite(product.toFavoriteEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isFavorite(productId: Int): Result<Boolean> {
        return try {
            val isFavorite = favoriteDao.isFavorite(productId)
            Result.success(isFavorite)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}