package com.vlodo_o.productcatalog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vlodo_o.productcatalog.ui.screens.details.ProductDetailsScreen
import com.vlodo_o.productcatalog.ui.screens.favorites.FavoritesScreen
import com.vlodo_o.productcatalog.ui.screens.product_list.ProductListScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductList.route
    ) {

        composable(Screen.ProductList.route) {
            ProductListScreen(
                onProductClick = { productId  ->
                    navController.navigate(Screen.ProductDetails.createRoute(productId))
                }
            )
        }

        composable(
            route = Screen.ProductDetails.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")!!
            ProductDetailsScreen(
                productId = id,
                onBack = { navController.navigateUp()}
            )
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen(
                onProductClick = { id ->
                    navController.navigate(Screen.ProductDetails.createRoute(id))
                }
            )
        }
    }
}