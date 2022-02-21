package com.example.restaurant.presentation.menu_items.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.RemoveCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.restaurant.R
import com.example.restaurant.domain.model.MenuItem

@Composable
fun MenuItem(
    menuItem: MenuItem,
) {
    val localImageUrl = menuItem.imageUrl.replace("localhost", "10.0.2.2")
    val (orderCount, setOrderCount) = remember { mutableStateOf(0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 12.dp
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberImagePainter(localImageUrl),
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
                            if (orderCount > 0) {
                                setOrderCount(orderCount - 1)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.RemoveCircle,
                                contentDescription = "Remove menu item",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Text(
                            text = orderCount.toString(),
                            fontSize = 24.sp,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                        IconButton(onClick = { setOrderCount(orderCount + 1) }) {
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