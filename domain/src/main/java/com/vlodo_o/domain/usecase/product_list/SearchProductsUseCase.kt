package com.vlodo_o.domain.usecase.product_list

import com.vlodo_o.domain.model.Product

class SearchProductsUseCase {

    operator fun invoke(products: List<Product>, query: String): List<Product> {
        if (query.isBlank()) return products

        return products.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }
}