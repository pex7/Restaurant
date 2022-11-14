package com.example.restaurant.data.remote

import com.example.restaurant.data.remote.dto.CategoriesDto
import com.example.restaurant.domain.model.MenuItems
import com.example.restaurant.domain.model.PreparationTime
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RestaurantApi {
    @GET("/menu")
    suspend fun getMenu(@Query("category") categoryName: String): MenuItems

    @GET("/categories")
    suspend fun getCategories(): CategoriesDto

    @POST("/order")
    suspend fun submitOrder(@Body menuIds: List<Int>): PreparationTime
}