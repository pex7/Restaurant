package com.example.restaurant.domain.use_case.get_cart_item_quantity

import android.util.Log
import com.example.restaurant.domain.model.CartItem
import com.example.restaurant.domain.model.MenuItem
import com.example.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCartItemQuantity @Inject constructor(
    private val repository: RestaurantRepository
) {
//    operator fun invoke(menuItem: MenuItem): Flow<Int> = flow {
//        val cartItem: CartItem? = repository.getCartItems().find { it.item.id == menuItem.id }
//        Log.i("cartItem", "menuItem: $menuItem")
//        Log.i("cartItem", "cartItem: $cartItem")
//        emit(cartItem?.quantity ?: 0)
//    }
}