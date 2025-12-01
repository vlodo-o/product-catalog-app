package com.vlodo_o.data.di

import com.vlodo_o.data.repository.FavoritesRepositoryImpl
import com.vlodo_o.data.repository.ProductRepositoryImpl
import com.vlodo_o.domain.repository.FavoritesRepository
import com.vlodo_o.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    abstract fun bindFavoritesRepository(
        impl: FavoritesRepositoryImpl
    ): FavoritesRepository
}