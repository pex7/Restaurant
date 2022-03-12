package com.example.restaurant.domain.use_case.get_menu

import com.example.restaurant.common.Resource
import com.example.restaurant.data.remote.dto.MenuItemDto
import com.example.restaurant.data.remote.dto.toMenuItem
import com.example.restaurant.domain.model.MenuItem
import com.example.restaurant.domain.model.MenuItems
import com.example.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMenu @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(category: String): Flow<Resource<List<MenuItem>>> = flow {
        try {
            emit(Resource.Loading<List<MenuItem>>())

            val menuItems = repository.getMenu(category).items.map { it.toMenuItem() }

            emit(Resource.Success<List<MenuItem>>(menuItems))
        } catch (e: HttpException) {
            emit(Resource.Error<List<MenuItem>>(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<MenuItem>>("Could not connect to the server"))
        }
    }
}