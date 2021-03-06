package com.example.restaurant.domain.use_case.add_to_cart

import android.util.Log
import com.example.restaurant.domain.model.MenuItem
import com.example.restaurant.domain.repository.RestaurantRepository
import javax.inject.Inject

class AddToCart @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(menuItem: MenuItem) {
        Log.i("cartItem", "add to cart: $menuItem")
        repository.addToCart(menuItem)
    }
}