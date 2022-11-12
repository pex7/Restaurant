package com.example.restaurant.presentation.categories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurant.common.Resource
import com.example.restaurant.domain.use_case.get_categories.GetCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategories: GetCategories
) : ViewModel() {
    private val _state = mutableStateOf(CategoriesListState())
    val state: State<CategoriesListState> = _state

    init {
        getAllCategories()
    }

    private fun getAllCategories() {
        getCategories().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        CategoriesListState(categories = result.data?.categories ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = CategoriesListState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = CategoriesListState(error = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}