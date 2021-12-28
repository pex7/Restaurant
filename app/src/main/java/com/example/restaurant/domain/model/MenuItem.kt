package com.example.restaurant.domain.model

data class MenuItem(
    val category: String,
    val description: String,
    val id: Int,
    val image_url: String,
    val name: String,
    val price: Int
)