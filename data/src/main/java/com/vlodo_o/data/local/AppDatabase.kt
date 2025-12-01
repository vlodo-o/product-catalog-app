package com.vlodo_o.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vlodo_o.data.local.dao.FavoriteProductDao
import com.vlodo_o.data.local.dao.ProductDao
import com.vlodo_o.data.local.entity.FavoriteProductEntity
import com.vlodo_o.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, FavoriteProductEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun favoriteProductDao(): FavoriteProductDao
}
