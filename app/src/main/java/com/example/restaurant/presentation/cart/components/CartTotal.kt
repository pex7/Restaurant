package com.example.restaurant.presentation.cart.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restaurant.presentation.cart.CartEvent
import com.example.restaurant.presentation.cart.CartViewModel

@Composable
fun CartTotal(
    viewModel: CartViewModel = hiltViewModel()
) {
    val cartTotal = viewModel.cartTotal.collectAsState()
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(
            onClick = { viewModel.onEvent(CartEvent.OnSubmitOrder) },
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Row {
                Text("Place Order", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(16.dp))
                Text("$${String.format("%.2f", cartTotal.value)}", fontWeight = FontWeight.Bold)
            }
        }
    }
}