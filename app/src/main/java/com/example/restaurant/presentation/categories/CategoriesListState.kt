package com.example.restaurant.presentation.categories

data class CategoriesListState(
    val isLoading: Boolean = false,
    val categories: List<String> = emptyList(),
    val error: String = ""
)
