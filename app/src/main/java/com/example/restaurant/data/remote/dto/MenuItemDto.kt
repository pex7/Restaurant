package com.example.restaurant.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MenuItemDto(
    val category: String,
    @Json(name = "description")
    val detailText: String,
    val id: Int,
    @Json(name = "image_url")
    val imageUrl: String,
    val name: String,
    val price: Int
)