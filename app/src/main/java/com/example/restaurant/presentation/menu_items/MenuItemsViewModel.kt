package com.example.restaurant.presentation.menu_items

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurant.common.Resource
import com.example.restaurant.domain.model.MenuItem
import com.example.restaurant.domain.use_case.add_to_cart.AddToCart
import com.example.restaurant.domain.use_case.get_cart_item_quantity.GetCartItemQuantity
import com.example.restaurant.domain.use_case.get_menu.GetMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuItemsViewModel @Inject constructor(
    private val getMenu: GetMenu,
    private val getCartItemQuantity: GetCartItemQuantity,
    private val addToCart: AddToCart
): ViewModel() {
    private val _state = mutableStateOf(MenuItemsListState())
    val state: State<MenuItemsListState> = _state

    private val _cartItemQuantity = mutableStateOf<Int>(0)
    val cartItemQuantity: State<Int> = _cartItemQuantity

    fun getMenuItems(category: String) {
        getMenu(category).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MenuItemsListState(menuItems = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = MenuItemsListState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = MenuItemsListState(error = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getItemQuantity(menuItem: MenuItem) {
        Log.i("cartItem", "TEST")
        getCartItemQuantity(menuItem).onEach { result ->
            _cartItemQuantity.value = result
        }.launchIn(viewModelScope)
    }

    fun addItem(menuItem: MenuItem) {
        Log.i("cartItem", "VM addItem: $menuItem")
        addToCart(menuItem)
    }
}