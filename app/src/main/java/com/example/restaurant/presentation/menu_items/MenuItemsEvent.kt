package com.example.restaurant.presentation.menu_items

import com.example.restaurant.domain.model.MenuItem

sealed class MenuItemsEvent {
    data class OnIncrementItemCount(val menuItem: MenuItem): MenuItemsEvent()
    data class OnDecrementItemCount(val menuItem: MenuItem): MenuItemsEvent()
}