package com.example.restaurant.presentation.menu_items.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.RemoveCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.restaurant.domain.model.MenuItem
import com.example.restaurant.presentation.menu_items.MenuItemsEvent
import com.example.restaurant.presentation.menu_items.MenuItemsViewModel

@Composable
fun MenuItem(
    menuItem: MenuItem,
    viewModel: MenuItemsViewModel = hiltViewModel()
) {
    val cartItems = viewModel.cartItems.collectAsState()
    val cartItem = cartItems.value.find { it.item.id == menuItem.id }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 12.dp
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberImagePainter(menuItem.imageUrl),
                contentDescription = "Item image",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column() {
                Text(text = menuItem.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = menuItem.detailText)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "$" + String.format("%.2f", menuItem.price),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            if (cartItem?.quantity ?: 0 > 0) {
                                viewModel.onEvent(MenuItemsEvent.OnDecrementItemCount(menuItem))
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.RemoveCircle,
                                contentDescription = "Remove menu item",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Text(
                            text = cartItem?.quantity?.toString() ?: "0",
                            fontSize = 24.sp,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                        IconButton(onClick = {
                            viewModel.onEvent(MenuItemsEvent.OnIncrementItemCount(menuItem))
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.AddCircle,
                                contentDescription = "Add menu item",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}