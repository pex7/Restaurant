package com.example.restaurant.data.repository

import android.util.Log
import com.example.restaurant.data.remote.RestaurantApi
import com.example.restaurant.data.remote.dto.CategoriesDto
import com.example.restaurant.data.remote.dto.MenuItemDto
import com.example.restaurant.domain.model.CartItem
import com.example.restaurant.domain.model.CartItems
import com.example.restaurant.domain.model.MenuItem
import com.example.restaurant.domain.model.MenuItems
import com.example.restaurant.domain.repository.RestaurantRepository
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi
) : RestaurantRepository {
    private val cartItems = mutableListOf<CartItem>(
        CartItem(
            quantity = 1,
            item = MenuItem(
                name = "Pizza",
                price = 12.50,
                detailText = "coool",
                imageUrl = "",
                id = 8,
                category = "Salads"
            )
        )
    )

    override fun getCartItems(): List<CartItem> {
        return cartItems
    }

    override fun addToCart(menuItem: MenuItem) {
        var newCartItem: CartItem? = null

        cartItems.forEach { cartItem ->
            if (cartItem.item.name == menuItem.name) {
                cartItem.quantity = cartItem.quantity + 1
            } else {
                newCartItem = CartItem(
                    quantity = 1,
                    item = menuItem
                )
            }
        }

        newCartItem?.let {
            cartItems.add(it)
        }
    }

    override fun removeFromCart(menuItem: MenuItem) {
        var removeItemIndex = -1

        cartItems.forEachIndexed { index, cartItem ->
            if (cartItem.item.name == menuItem.name) {
                if (cartItem.quantity > 1) {
                    cartItem.quantity = cartItem.quantity - 1
                } else {
                    removeItemIndex = index
                }
            }
        }

        if (removeItemIndex != -1) {
            cartItems.removeAt(removeItemIndex)
        }
    }

    override suspend fun getMenu(category: String): MenuItems {
        return api.getMenu(category)
    }

    override suspend fun getCategories(): CategoriesDto {
        return api.getCategories()
    }
}