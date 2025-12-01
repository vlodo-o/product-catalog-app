package com.vlodo_o.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vlodo_o.data.local.entity.FavoriteProductEntity

@Dao
interface FavoriteProductDao {

    @Query("SELECT * FROM favorite_products")
    suspend fun getAllFavorites(): List<FavoriteProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(product: FavoriteProductEntity)

    @Delete
    suspend fun removeFavorite(product: FavoriteProductEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_products WHERE id = :productId)")
    suspend fun isFavorite(productId: Int): Boolean
}