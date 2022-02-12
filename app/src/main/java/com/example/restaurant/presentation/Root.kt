package com.example.restaurant.presentation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.restaurant.BottomNavItem
import com.example.restaurant.presentation.ui.theme.RestaurantTheme


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Root() {
    RestaurantTheme {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavigationBar(items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = "home",
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(
                        name = "Cart",
                        route = "cart",
                        icon = Icons.Default.ShoppingCart
                    ),
                    BottomNavItem(
                        name = "Orders",
                        route = "orders",
                        icon = Icons.Default.Settings
                    )
                ),
                    navController = navController,
                    onItemClick = {
                        navController.navigate(it.route)
                    }
                )
            }
        ) {
            Navigation(navController = navController)
        }
    }
}