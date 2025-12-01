package com.vlodo_o.productcatalog.navigation

sealed class Screen(val route: String) {

    object ProductList : Screen("product_list")

    object ProductDetails : Screen("product_details/{id}") {
        fun createRoute(id: Int) = "product_details/$id"
    }

    object Favorites : Screen("favorites")
}