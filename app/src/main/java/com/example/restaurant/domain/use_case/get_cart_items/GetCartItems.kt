package com.example.restaurant.domain.use_case.get_cart_items

import com.example.restaurant.domain.model.CartItem
import com.example.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCartItems @Inject constructor(
    val repository: RestaurantRepository
) {
//    operator fun invoke(): Flow<List<CartItem>> = flow {
//        val cartItems = repository.getCartItems()
//        emit(cartItems)
//    }
}