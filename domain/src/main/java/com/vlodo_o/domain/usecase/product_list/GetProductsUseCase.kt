package com.vlodo_o.domain.usecase.product_list

import com.vlodo_o.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke() = repository.getProducts()
}