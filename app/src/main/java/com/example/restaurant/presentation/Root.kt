package com.example.restaurant.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.restaurant.BottomNavItem
import com.example.restaurant.presentation.root.RootViewModel
import com.example.restaurant.presentation.ui.theme.RestaurantTheme

@Composable
fun Root(
    viewModel: RootViewModel = hiltViewModel()
) {
    val cartQuantity = viewModel.cartQuantity.collectAsState()

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
                        icon = Icons.Default.ShoppingCart,
                        badgeCount = cartQuantity.value
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