package com.example.restaurant.presentation.cart.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.RemoveCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restaurant.domain.model.CartItem
import com.example.restaurant.presentation.cart.CartItemEvent
import com.example.restaurant.presentation.cart.CartViewModel

@Composable
fun CartItemCounter(
    cartItem: CartItem,
    viewModel: CartViewModel = hiltViewModel(),
    iconSize: Int = 32,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {
            if (cartItem?.quantity ?: 0 > 0) {
                viewModel.onEvent(CartItemEvent.OnDecrementItemCount(cartItem.item))
            }
        }) {
            Icon(
                imageVector = Icons.Outlined.RemoveCircle,
                contentDescription = "Remove menu item",
                modifier = Modifier.size(iconSize.dp)
            )
        }
        Text(
            text = cartItem?.quantity?.toString() ?: "0",
            fontSize = 24.sp,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        IconButton(onClick = {
            viewModel.onEvent(CartItemEvent.OnIncrementItemCount(cartItem.item))
        }) {
            Icon(
                imageVector = Icons.Outlined.AddCircle,
                contentDescription = "Add menu item",
                modifier = Modifier.size(iconSize.dp)
            )
        }
    }
}