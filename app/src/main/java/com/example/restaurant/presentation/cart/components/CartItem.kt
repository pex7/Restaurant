package com.example.restaurant.presentation.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.RemoveCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.restaurant.domain.model.CartItem
import com.example.restaurant.presentation.cart.CartItemEvent
import com.example.restaurant.presentation.cart.CartViewModel

@Composable
fun CartItem(
    cartItem: CartItem,
    viewModel: CartViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberImagePainter(cartItem.item.imageUrl),
                contentDescription = "Item image",
                modifier = Modifier.size(50.dp)
            )
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Row() {
                    Text(cartItem.item.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { viewModel.onEvent(CartItemEvent.OnRemoveItem(cartItem.item)) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove menu item",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    CartItemCounter(cartItem = cartItem, iconSize = 24)
                    Text(
                        text = "$${String.format("%.2f", cartItem.quantity * cartItem.item.price)}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}