package com.example.restaurant.presentation.menu_items

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurant.common.Resource
import com.example.restaurant.domain.use_case.get_menu.GetMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MenuItemsViewModel @Inject constructor(
    private val getMenu: GetMenu
): ViewModel() {
    private val _state = mutableStateOf(MenuItemsListState())
    val state: State<MenuItemsListState> = _state

    fun getMenuItems(category: String) {
        getMenu(category).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MenuItemsListState(menuItems = result.data?.items ?: emptyList())
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