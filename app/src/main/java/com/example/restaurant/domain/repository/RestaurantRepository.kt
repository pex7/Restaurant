package com.example.restaurant.domain.repository

import com.example.restaurant.data.remote.dto.CategoriesDto
import com.example.restaurant.data.remote.dto.MenuItemDto
import com.example.restaurant.domain.model.MenuItems

interface RestaurantRepository {
    suspend fun getMenu(category: String): MenuItems

    suspend fun getCategories(): CategoriesDto
}