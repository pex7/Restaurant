package com.example.restaurant.di

import com.example.restaurant.common.Constants
import com.example.restaurant.data.remote.RestaurantApi
import com.example.restaurant.data.repository.RestaurantRepositoryImpl
import com.example.restaurant.domain.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRestaurantApi(): RestaurantApi {
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RestaurantApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRestaurantRepository(api: RestaurantApi): RestaurantRepository {
        return RestaurantRepositoryImpl(api)
    }
}