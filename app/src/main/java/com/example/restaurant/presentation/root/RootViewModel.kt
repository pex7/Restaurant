package com.example.restaurant.presentation.root

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RootViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {
    private val _state = mutableStateOf(RootState())
    val state: State<RootState> = _state

    val cartQuantity = MutableStateFlow(0)

    init {
        getCartQuantity()
    }

    private fun getCartQuantity() {
        viewModelScope.launch {
            repository.getCartItems().collect {
                cartQuantity.value = it.fold(0) { acc, item ->
                    acc + item.quantity
                }
            }
        }
    }
}