package com.vlodo_o.domain.usecase.product_list

import com.vlodo_o.domain.model.LoadDataResult
import com.vlodo_o.domain.model.Product
import com.vlodo_o.domain.model.ProductCategory
import com.vlodo_o.domain.repository.ProductRepository

class GetProductsByCategoryUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(category: ProductCategory?): LoadDataResult<List<Product>> {
        return when (val result = repository.getProducts()) {
            is LoadDataResult.Success -> {
                val filtered = if (category == null) {
                    result.data
                } else {
                    result.data.filter { ProductCategory.fromDisplayName(it.category) == category }
                }
                LoadDataResult.Success(
                    data = filtered,
                    isOffline = result.isOffline
                )
            }
            is LoadDataResult.Error -> result
        }
    }
}