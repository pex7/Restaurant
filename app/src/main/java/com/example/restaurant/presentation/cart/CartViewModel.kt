package com.example.restaurant.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurant.domain.model.CartItem
import com.example.restaurant.domain.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {
    val cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartTotal = MutableStateFlow(0.00)
    val submitOrderState = MutableStateFlow(SubmitOrderState())

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

    fun onEvent(event: CartEvent) {
        when (event) {
            is CartEvent.OnDecrementItemCount -> {
                viewModelScope.launch {
                    repository.decrementCartItem(event.menuItem)
                }
            }
            is CartEvent.OnIncrementItemCount -> {
                viewModelScope.launch {
                    repository.incrementCartItem(event.menuItem)
                }
            }
            is CartEvent.OnRemoveItem -> {
                viewModelScope.launch {
                    repository.removeCartItem(event.menuItem)
                }
            }
            is CartEvent.OnSubmitOrder -> {
                val menuIds = cartItems.value.map { cartItem -> cartItem.item.id }

                viewModelScope.launch {
                    try {
                        submitOrderState.value = SubmitOrderState(isLoading = true)
                        val response = repository.submitOrder(menuIds)
                        repository.clearCart()
                        submitOrderState.value = SubmitOrderState(prepTime = response.prepTime)
                    } catch (e: HttpException) {
                        submitOrderState.value =
                            SubmitOrderState(error = e.localizedMessage ?: "An error occurred")
                    }
                }
            }
            is CartEvent.OnPrepTimeToastShown -> {
                submitOrderState.value = SubmitOrderState(prepTime = -1)
            }
        }
    }
}