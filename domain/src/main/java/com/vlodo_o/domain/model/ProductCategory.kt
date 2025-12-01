package com.vlodo_o.domain.model

enum class ProductCategory(val displayName: String) {
    MENS_CLOTHING("men's clothing"),
    JEWELRY("jewelery"),
    ELECTRONICS("electronics"),
    WOMENS_CLOTHING("women's clothing");

    companion object {
        fun fromDisplayName(name: String): ProductCategory? {
            return ProductCategory.entries.firstOrNull { it.displayName.equals(name, ignoreCase = true) }
        }

        fun allCategoriesNames(): List<String> = entries.map { it.displayName }
    }
}