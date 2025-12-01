package com.vlodo_o.productcatalog.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.vlodo_o.productcatalog.ui.screens.details.ProductDetailsScreen
import com.vlodo_o.productcatalog.ui.screens.product_list.ProductListScreen

fun NavGraphBuilder.homeGraph(onProductClick: (Int) -> Unit) {
    navigation(startDestination = "product_list", route = "home_graph") {
        composable("product_list") {
            ProductListScreen(onProductClick = onProductClick)
        }

        composable(
            route = "home_graph/product_details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            ProductDetailsScreen(productId = id, onBack = { })
        }
    }
}