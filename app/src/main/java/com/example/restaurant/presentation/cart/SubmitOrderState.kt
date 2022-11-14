package com.example.restaurant.presentation.cart

data class SubmitOrderState(
    val isLoading: Boolean = false,
    val prepTime: Int = -1,
    val error: String = ""
)
