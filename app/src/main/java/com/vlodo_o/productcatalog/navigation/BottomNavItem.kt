package com.vlodo_o.productcatalog.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.vlodo_o.productcatalog.R

sealed class BottomNavItem(
    @StringRes val titleResource: Int,
    val graph: String,
    val icon: ImageVector
) {
    abstract fun startDestination(): String

    object Home : BottomNavItem(
        R.string.home,
        "home_graph",
        Icons.Default.Home
    ) {
        override fun startDestination() = "product_list"
    }

    object Favorites : BottomNavItem(
        R.string.favorites,
        "favorites_graph",
        Icons.Default.Favorite
    ) {
        override fun startDestination() = "favorites"
    }
}
