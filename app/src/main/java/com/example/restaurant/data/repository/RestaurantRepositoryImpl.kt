package com.example.restaurant.data.repository

import com.example.restaurant.data.remote.RestaurantApi
import com.example.restaurant.data.remote.dto.CategoriesDto
import com.example.restaurant.domain.model.CartItem
import com.example.restaurant.domain.model.MenuItem
import com.example.restaurant.domain.model.MenuItems
import com.example.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi
) : RestaurantRepository {
    private var _cartItems = MutableStateFlow(mutableListOf<CartItem>())

    private val cartItems = _cartItems.asStateFlow()

    override suspend fun getCartItems(): Flow<List<CartItem>> {
        return cartItems
    }

    override suspend fun incrementCartItem(menuItem: MenuItem) {
        var newCartItems = mutableListOf<CartItem>()
        var isNewCartItem = true

        cartItems.value.forEach { cartItem ->
            if (cartItem.item.id == menuItem.id) {
                isNewCartItem = false
                newCartItems.add(
                    CartItem(
                        quantity = cartItem.quantity + 1,
                        item = cartItem.item
                    )
                )
            } else {
                newCartItems.add(cartItem)
            }
        }

        if (isNewCartItem) {
            newCartItems.add(
                CartItem(
                    quantity = 1,
                    item = menuItem
                )
            )
        }

        _cartItems.update { newCartItems }
    }

    override suspend fun decrementCartItem(menuItem: MenuItem) {
        var newCartItems = mutableListOf<CartItem>()

        cartItems.value.forEach { cartItem ->
            if (cartItem.item.id == menuItem.id) {
                if (cartItem.quantity > 1) {
                    newCartItems.add(
                        CartItem(
                            quantity = cartItem.quantity - 1,
                            item = cartItem.item
                        )
                    )
                }
            } else {
                newCartItems.add(cartItem)
            }
        }

        _cartItems.update { newCartItems }
    }

    override suspend fun removeCartItem(menuItem: MenuItem) {
        var newCartItems = mutableListOf<CartItem>()

        cartItems.value.forEach { cartItem ->
            if (cartItem.item.id != menuItem.id) {
                newCartItems.add(cartItem)
            }
        }

        _cartItems.update { newCartItems }
    }

    override suspend fun getMenu(category: String): MenuItems {
        return api.getMenu(category)
    }

    override suspend fun getCategories(): CategoriesDto {
        return api.getCategories()
    }
}