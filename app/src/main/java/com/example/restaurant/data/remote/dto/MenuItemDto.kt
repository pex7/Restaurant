package com.example.restaurant.data.remote.dto

import com.example.restaurant.domain.model.MenuItem
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
    val price: Double
)

fun MenuItemDto.toMenuItem(): MenuItem {
    return MenuItem(
        category = category,
        detailText = detailText,
        id = id,
        imageUrl = imageUrl.replace("localhost", "10.0.2.2"),
        name = name,
        price = price
    )
}