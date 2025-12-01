package com.vlodo_o.productcatalog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home_graph") {
        homeGraph { id ->
            navController.navigate("home_graph/product_details/$id")
        }
        favoritesGraph { id ->
            navController.navigate("favorites_graph/product_details/$id")
        }
    }
}