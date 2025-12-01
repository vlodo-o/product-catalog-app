package com.vlodo_o.data.repository

import com.vlodo_o.data.local.dao.ProductDao
import com.vlodo_o.data.local.mapper.toDomain
import com.vlodo_o.data.local.mapper.toEntity
import com.vlodo_o.data.remote.api.ProductApiService
import com.vlodo_o.data.remote.mapper.toDomain
import com.vlodo_o.domain.model.LoadDataResult
import com.vlodo_o.domain.model.Product
import com.vlodo_o.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ProductApiService,
    private val productDao: ProductDao,
) : ProductRepository {

    override suspend fun getProducts(): LoadDataResult<List<Product>> {
        return try {
            val products = apiService.getProducts().map { it.toDomain() }
            productDao.insertProducts(products.map { it.toEntity() })
            LoadDataResult.Success(products, isOffline = false)
        } catch (e: Exception) {
            val cached = productDao.getAllProducts().map { it.toDomain() }
            if (cached.isNotEmpty()) LoadDataResult.Success(cached, isOffline = true)
            else LoadDataResult.Error(e)
        }
    }

    override suspend fun getProductById(id: Int): LoadDataResult<Product> {
        return try {
            val product = apiService.getProductById(id).toDomain()
            LoadDataResult.Success(product, isOffline = false)
        } catch (e: Exception) {
            val cached = productDao.getProductById(id)?.toDomain()
            if (cached != null) LoadDataResult.Success(cached, isOffline = true)
            else LoadDataResult.Error(e)
        }
    }
}