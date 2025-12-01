package com.vlodo_o.data.di

import android.content.Context
import androidx.room.Room
import com.vlodo_o.data.local.AppDatabase
import com.vlodo_o.data.local.dao.FavoriteProductDao
import com.vlodo_o.data.local.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteProductDao(database: AppDatabase): FavoriteProductDao {
        return database.favoriteProductDao()
    }

}