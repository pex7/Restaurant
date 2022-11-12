package com.example.restaurant.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurant.domain.model.CartItem
import com.example.restaurant.domain.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {
    val cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartTotal = MutableStateFlow(0.00)

    init {
        getCartTotal()
    }

    private fun getCartTotal() {
        viewModelScope.launch {
            repository.getCartItems().collect {
                cartItems.value = it
                cartTotal.value = it.fold(0.00) { acc, cartItem ->
                    acc + cartItem.quantity * cartItem.item.price
                }
            }
        }
    }

    fun onEvent(event: CartItemEvent) {
        when (event) {
            is CartItemEvent.OnDecrementItemCount -> {
                viewModelScope.launch {
                    repository.decrementCartItem(event.menuItem)
                }
            }
            is CartItemEvent.OnIncrementItemCount -> {
                viewModelScope.launch {
                    repository.incrementCartItem(event.menuItem)
                }
            }
            is CartItemEvent.OnRemoveItem -> {
                viewModelScope.launch {
                    repository.removeCartItem(event.menuItem)
                }
            }
        }
    }
}