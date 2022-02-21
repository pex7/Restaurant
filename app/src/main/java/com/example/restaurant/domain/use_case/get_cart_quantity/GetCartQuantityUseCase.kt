package com.example.restaurant.domain.use_case.get_cart_quantity

import com.example.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCartQuantityUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(): Flow<Int> = flow {
        emit(repository.getCartItems().size)
    }
}