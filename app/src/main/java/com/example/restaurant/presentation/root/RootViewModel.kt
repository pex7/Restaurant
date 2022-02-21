package com.example.restaurant.presentation.root

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.restaurant.domain.use_case.get_cart_quantity.GetCartQuantityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import androidx.lifecycle.viewModelScope

@HiltViewModel
class RootViewModel @Inject constructor(
    private val getCartQuantityUseCase: GetCartQuantityUseCase
): ViewModel() {
    private val _state = mutableStateOf(RootState())
    val state: State<RootState> = _state

    init {
        getCartQuantity()
    }

    private fun getCartQuantity() {
        getCartQuantityUseCase().onEach {
            _state.value = RootState(cartQuantity = it)
        }.launchIn(viewModelScope)
    }
}