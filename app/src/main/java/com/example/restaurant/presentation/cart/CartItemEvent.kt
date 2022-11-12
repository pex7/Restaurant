package com.example.restaurant.presentation.cart

import com.example.restaurant.domain.model.MenuItem

sealed class CartItemEvent {
    data class OnIncrementItemCount(val menuItem: MenuItem) : CartItemEvent()
    data class OnDecrementItemCount(val menuItem: MenuItem) : CartItemEvent()
    data class OnRemoveItem(val menuItem: MenuItem) : CartItemEvent()
}
