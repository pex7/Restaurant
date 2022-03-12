package com.example.restaurant.presentation.menu_items

import com.example.restaurant.domain.model.MenuItem

data class MenuItemsListState(
    val isLoading: Boolean = false,
    val menuItems: List<MenuItem> = emptyList(),
    val error: String = "",
)
