package com.example.restaurant.data.repository

import com.example.restaurant.data.remote.RestaurantApi
import com.example.restaurant.data.remote.dto.MenuItemDto
import com.example.restaurant.domain.repository.RestaurantRepository
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi
) : RestaurantRepository {
    override suspend fun getMenu(): List<MenuItemDto> {
        return api.getMenu()
    }

    override suspend fun getCategories(): List<String> {
        return api.getCategories()
    }
}