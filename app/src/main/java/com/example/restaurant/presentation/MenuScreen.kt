package com.example.restaurant.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.restaurant.presentation.menu_items.components.MenuItemsList

@Composable
fun MenuScreen(category: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (category != null) {
            MenuItemsList(category = category)
        }
    }
}