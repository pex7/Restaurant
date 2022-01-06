package com.example.restaurant.domain.use_case.get_menu

import com.example.restaurant.common.Resource
import com.example.restaurant.data.remote.dto.MenuItemDto
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
    operator fun invoke(category: String): Flow<Resource<MenuItems>> = flow {
        try {
            emit(Resource.Loading<MenuItems>())

            val menuItems = repository.getMenu(category)

            emit(Resource.Success<MenuItems>(menuItems))
        } catch (e: HttpException) {
            emit(Resource.Error<MenuItems>(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<MenuItems>("Could not connect to the server"))
        }
    }
}