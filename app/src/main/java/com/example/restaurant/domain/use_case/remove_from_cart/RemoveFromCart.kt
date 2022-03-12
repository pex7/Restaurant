package com.example.restaurant.domain.use_case.remove_from_cart

import com.example.restaurant.domain.model.MenuItem
import com.example.restaurant.domain.repository.RestaurantRepository
import javax.inject.Inject

class RemoveFromCart @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(menuItem: MenuItem) {
        repository.removeFromCart(menuItem)
    }
}