package com.example.restaurant.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrepatationTimeDto(
    val prepTime: Int
)
