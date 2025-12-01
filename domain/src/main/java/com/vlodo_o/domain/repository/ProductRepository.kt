package com.vlodo_o.domain.repository

import com.vlodo_o.domain.model.LoadDataResult
import com.vlodo_o.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): LoadDataResult<List<Product>>
    suspend fun getProductById(id: Int): LoadDataResult<Product>
}