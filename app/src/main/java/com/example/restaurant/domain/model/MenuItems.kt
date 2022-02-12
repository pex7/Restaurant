package com.example.restaurant.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MenuItems(
    val items: List<MenuItem>
)
