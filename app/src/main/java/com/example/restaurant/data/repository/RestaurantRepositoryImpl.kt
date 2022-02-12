package com.example.restaurant.data.repository

import android.util.Log
import com.example.restaurant.data.remote.RestaurantApi
import com.example.restaurant.data.remote.dto.CategoriesDto
import com.example.restaurant.data.remote.dto.MenuItemDto
import com.example.restaurant.domain.model.MenuItems
import com.example.restaurant.domain.repository.RestaurantRepository
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi
) : RestaurantRepository {
    override suspend fun getMenu(category: String): MenuItems {
        return api.getMenu(category)
    }

    override suspend fun getCategories(): CategoriesDto {
        return api.getCategories()
    }
}