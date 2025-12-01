package com.vlodo_o.productcatalog.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.vlodo_o.productcatalog.ui.screens.details.ProductDetailsScreen
import com.vlodo_o.productcatalog.ui.screens.favorites.FavoritesScreen

fun NavGraphBuilder.favoritesGraph(onProductClick: (Int) -> Unit) {
    navigation(startDestination = "favorites", route = "favorites_graph") {
        composable("favorites") {
            FavoritesScreen(onProductClick = onProductClick)
        }

        composable(
            route = "favorites_graph/product_details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("id")
            ProductDetailsScreen(productId = id, onBack = {  })
        }
    }
}
