package com.vlodo_o.productcatalog.di

import com.vlodo_o.domain.repository.FavoritesRepository
import com.vlodo_o.domain.repository.ProductRepository
import com.vlodo_o.domain.usecase.details.GetProductByIdUseCase
import com.vlodo_o.domain.usecase.favorites.AddToFavoritesUseCase
import com.vlodo_o.domain.usecase.favorites.GetFavoriteProductsUseCase
import com.vlodo_o.domain.usecase.favorites.IsFavoriteUseCase
import com.vlodo_o.domain.usecase.favorites.RemoveFromFavoritesUseCase
import com.vlodo_o.domain.usecase.product_list.GetCategoriesUseCase
import com.vlodo_o.domain.usecase.product_list.GetProductsByCategoryUseCase
import com.vlodo_o.domain.usecase.product_list.GetProductsUseCase
import com.vlodo_o.domain.usecase.product_list.SearchProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetProductsUseCase(
        repository: ProductRepository
    ): GetProductsUseCase = GetProductsUseCase(repository)

    @Provides
    fun provideGetCategoriesUseCase(
        repository: ProductRepository
    ): GetCategoriesUseCase = GetCategoriesUseCase()

    @Provides
    fun provideGetProductsByCategoryUseCase(
        repository: ProductRepository
    ): GetProductsByCategoryUseCase = GetProductsByCategoryUseCase(repository)

    @Provides
    fun provideSearchProductsUseCase(
        repository: ProductRepository
    ): SearchProductsUseCase = SearchProductsUseCase()


    @Provides
    fun provideGetProductByIdUseCase(
        repository: ProductRepository
    ): GetProductByIdUseCase = GetProductByIdUseCase(repository)


    @Provides
    fun provideAddToFavoritesUseCase(
        favoritesRepository: FavoritesRepository
    ): AddToFavoritesUseCase = AddToFavoritesUseCase(favoritesRepository)

    @Provides
    fun provideGetFavoriteProductsUseCase(
        favoritesRepository: FavoritesRepository
    ): GetFavoriteProductsUseCase = GetFavoriteProductsUseCase(favoritesRepository)

    @Provides
    fun provideRemoveFromFavoritesUseCase(
        favoritesRepository: FavoritesRepository
    ): RemoveFromFavoritesUseCase = RemoveFromFavoritesUseCase(favoritesRepository)

    @Provides
    fun provideIsFavoriteUseCase(
        favoritesRepository: FavoritesRepository
    ): IsFavoriteUseCase = IsFavoriteUseCase(favoritesRepository)

}