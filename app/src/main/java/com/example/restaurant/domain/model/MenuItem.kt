package com.example.restaurant.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MenuItem(
    val category: String,
    @Json(name = "description")
    val detailText: String,
    val id: Int,
    @Json(name = "image_url")
    val imageUrl: String,
    val name: String,
    val price: Int
)