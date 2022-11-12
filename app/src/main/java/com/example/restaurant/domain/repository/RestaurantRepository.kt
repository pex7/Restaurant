package com.example.restaurant.domain.repository

import com.example.restaurant.data.remote.dto.CategoriesDto
import com.example.restaurant.domain.model.CartItem
import com.example.restaurant.domain.model.MenuItem
import com.example.restaurant.domain.model.MenuItems
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {
    suspend fun getMenu(category: String): MenuItems

    suspend fun getCategories(): CategoriesDto

    suspend fun getCartItems(): Flow<List<CartItem>>

    suspend fun incrementCartItem(menuItem: MenuItem): Unit

    suspend fun decrementCartItem(menuItem: MenuItem): Unit

    suspend fun removeCartItem(menuItem: MenuItem)
}