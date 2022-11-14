package com.example.restaurant.presentation.menu_items

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurant.common.Resource
import com.example.restaurant.domain.model.CartItem
import com.example.restaurant.domain.repository.RestaurantRepository
import com.example.restaurant.domain.use_case.get_menu.GetMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuItemsViewModel @Inject constructor(
    private val getMenu: GetMenu,
    private val repository: RestaurantRepository
) : ViewModel() {
    private val _state = mutableStateOf(MenuItemsListState())
    val state: State<MenuItemsListState> = _state

    val cartItems = MutableStateFlow<List<CartItem>>(emptyList())

    init {
        getCartItems()
    }

    private fun getCartItems() {
        viewModelScope.launch {
            repository.getCartItems().collect {
                cartItems.value = it
            }
        }
    }

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
}