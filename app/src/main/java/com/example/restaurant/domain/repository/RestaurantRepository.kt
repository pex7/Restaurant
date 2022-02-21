package com.example.restaurant.domain.repository

import com.example.restaurant.data.remote.dto.CategoriesDto
import com.example.restaurant.data.remote.dto.MenuItemDto
import com.example.restaurant.domain.model.CartItem
import com.example.restaurant.domain.model.CartItems
import com.example.restaurant.domain.model.MenuItem
import com.example.restaurant.domain.model.MenuItems

interface RestaurantRepository {
    suspend fun getMenu(category: String): MenuItems

    suspend fun getCategories(): CategoriesDto

    fun getCartItems(): List<CartItem>

    fun addToCart(menuItem: MenuItem): Unit

    fun removeFromCart(menuItem: MenuItem): Unit
}