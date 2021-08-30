package com.test.fptestproject.di

import com.test.fptestproject.data.APIManager
import com.test.fptestproject.data.WeatherRepository
import com.test.fptestproject.data.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(apiManager: APIManager): WeatherRepository {
        return WeatherRepositoryImpl(apiManager)
    }
}