package com.example.restaurant.data.remote

import com.example.restaurant.data.remote.dto.CategoriesDto
import com.example.restaurant.data.remote.dto.MenuItemDto
import retrofit2.http.GET

interface RestaurantApi {
    @GET("/menu")
    suspend fun getMenu(): List<MenuItemDto>

    @GET("/categories")
    suspend fun getCategories(): CategoriesDto
}