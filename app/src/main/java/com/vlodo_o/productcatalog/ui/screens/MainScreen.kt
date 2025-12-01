package com.vlodo_o.productcatalog.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.vlodo_o.productcatalog.navigation.AppNavHost
import com.vlodo_o.productcatalog.navigation.BottomNavItem

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val tabs = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorites
    )

    var selectedTab by rememberSaveable { mutableStateOf(BottomNavItem.Home.graph) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEach { tab ->
                    val isSelected = selectedTab == tab.graph
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            if (isSelected) {
                                navController.popBackStack(
                                    route = tab.startDestination(),
                                    inclusive = false
                                )
                            } else {
                                selectedTab = tab.graph
                                navController.navigate(tab.graph) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.id) { saveState = true }
                                }
                            }
                        },
                        icon = { Icon(tab.icon, contentDescription = null) },
                        label = { Text(stringResource(tab.titleResource)) }
                    )
                }
            }
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            AppNavHost(navController)
        }
    }
}