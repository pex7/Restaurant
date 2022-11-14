package com.example.restaurant.presentation.cart

import com.example.restaurant.domain.model.MenuItem

sealed class CartEvent {
    data class OnIncrementItemCount(val menuItem: MenuItem) : CartEvent()
    data class OnDecrementItemCount(val menuItem: MenuItem) : CartEvent()
    data class OnRemoveItem(val menuItem: MenuItem) : CartEvent()
    object OnSubmitOrder : CartEvent()
    object OnPrepTimeToastShown : CartEvent()
}
