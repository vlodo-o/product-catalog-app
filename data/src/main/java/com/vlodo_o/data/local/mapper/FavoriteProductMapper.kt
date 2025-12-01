package com.vlodo_o.data.local.mapper

import com.vlodo_o.data.local.entity.FavoriteProductEntity
import com.vlodo_o.domain.model.Product

fun Product.toFavoriteEntity(): FavoriteProductEntity = FavoriteProductEntity(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    imageUrl = imageUrl
)

fun FavoriteProductEntity.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        imageUrl = imageUrl
    )
}
