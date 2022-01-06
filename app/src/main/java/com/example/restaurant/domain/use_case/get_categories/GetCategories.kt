package com.example.restaurant.domain.use_case.get_categories

import com.example.restaurant.common.Resource
import com.example.restaurant.data.remote.dto.CategoriesDto
import com.example.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(): Flow<Resource<CategoriesDto>> = flow {
        try {
            emit(Resource.Loading<CategoriesDto>())

            val categories = repository.getCategories()

            emit(Resource.Success<CategoriesDto>(categories))
        } catch (e: HttpException) {
            emit(Resource.Error<CategoriesDto>(e.localizedMessage ?: "An error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<CategoriesDto>(e.localizedMessage))
        }
    }
}