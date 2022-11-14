package com.example.restaurant.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restaurant.presentation.cart.CartEvent
import com.example.restaurant.presentation.cart.CartViewModel
import com.example.restaurant.presentation.cart.components.CartList
import com.example.restaurant.presentation.cart.components.CartTotal

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel()
) {
    val submitOrderState = viewModel.submitOrderState.collectAsState()
    val successMessage = "Your order will be prepared in ${submitOrderState.value.prepTime} minutes"
    val toastMessage = submitOrderState.value.error.ifBlank { successMessage }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            CartList()
            CartTotal()

            if (submitOrderState.value.prepTime > -1 || submitOrderState.value.error.isNotBlank()) {
                Toast.makeText(
                    LocalContext.current,
                    toastMessage,
                    Toast.LENGTH_LONG
                ).show()
                viewModel.onEvent(CartEvent.OnPrepTimeToastShown)
            }
        }
        if (submitOrderState.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}