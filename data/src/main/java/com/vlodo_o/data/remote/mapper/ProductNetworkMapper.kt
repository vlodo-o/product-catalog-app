package com.vlodo_o.data.remote.mapper

import com.vlodo_o.data.remote.dto.ProductDto
import com.vlodo_o.domain.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        imageUrl = image
    )
}