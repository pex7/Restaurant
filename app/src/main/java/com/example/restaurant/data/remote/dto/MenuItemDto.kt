package com.example.restaurant.data.remote.dto

import com.squareup.moshi.Json

data class MenuItemDto(
    val category: String,
    val description: String,
    val id: Int,
    @Json(name = "image_url")
    val imageUrl: String,
    val name: String,
    val price: Int
)