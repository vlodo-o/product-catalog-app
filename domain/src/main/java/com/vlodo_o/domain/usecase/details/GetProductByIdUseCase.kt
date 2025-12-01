package com.vlodo_o.domain.usecase.details

import com.vlodo_o.domain.repository.ProductRepository

class GetProductByIdUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Int) = repository.getProductById(id)
}