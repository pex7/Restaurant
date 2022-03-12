package com.example.restaurant.domain.model

import com.example.restaurant.data.remote.dto.MenuItemDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MenuItems(
    val items: List<MenuItemDto>
)
