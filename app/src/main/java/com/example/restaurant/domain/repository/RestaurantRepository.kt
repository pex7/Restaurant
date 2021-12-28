package com.example.restaurant.domain.repository

import com.example.restaurant.data.remote.dto.MenuItemDto

interface RestaurantRepository {
    suspend fun getMenu(): List<MenuItemDto>

    suspend fun getCategories(): List<String>
}