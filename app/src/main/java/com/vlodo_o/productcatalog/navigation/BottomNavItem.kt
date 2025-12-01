package com.vlodo_o.productcatalog.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.vlodo_o.productcatalog.R

sealed class BottomNavItem(
    @StringRes val titleResource: Int,
    val route: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        titleResource = R.string.home,
        route = Screen.ProductList.route,
        icon = Icons.Default.Home
    )

    object Favorites : BottomNavItem(
        titleResource = R.string.favorites,
        route = Screen.Favorites.route,
        icon = Icons.Default.Favorite
    )
}