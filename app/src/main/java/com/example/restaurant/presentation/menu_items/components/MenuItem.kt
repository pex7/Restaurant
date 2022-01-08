package com.example.restaurant.presentation.menu_items.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.restaurant.domain.model.MenuItem

@Composable
fun MenuItem(
    menuItem: MenuItem,
) {
    Text(text = menuItem.name)
}