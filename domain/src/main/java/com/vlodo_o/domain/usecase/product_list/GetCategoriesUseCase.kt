package com.vlodo_o.domain.usecase.product_list

import com.vlodo_o.domain.model.ProductCategory

class GetCategoriesUseCase {
    operator fun invoke(): List<String> {
        return ProductCategory.allCategoriesNames()
    }
}