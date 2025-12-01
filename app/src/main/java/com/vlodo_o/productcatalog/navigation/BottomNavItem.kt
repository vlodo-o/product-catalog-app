package com.vlodo_o.productcatalog.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        title = "Home",
        route = Screen.ProductList.route,
        icon = Icons.Default.Home
    )

    object Favorites : BottomNavItem(
        title = "Favorites",
        route = Screen.Favorites.route,
        icon = Icons.Default.Favorite
    )
}