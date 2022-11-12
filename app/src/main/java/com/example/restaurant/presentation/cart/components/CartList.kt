package com.example.restaurant.presentation.cart.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restaurant.presentation.cart.CartViewModel

@Composable
fun CartList(
    viewModel: CartViewModel = hiltViewModel()
) {
    val cartItems = viewModel.cartItems.collectAsState()

    Box(modifier = Modifier
        .padding(8.dp)) {
        LazyColumn() {
            items(cartItems.value) { cartItem ->
                CartItem(cartItem)
            }
        }
    }
}