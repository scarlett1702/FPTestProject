package com.test.fptestproject.data

import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherRepositoryImpl @Inject constructor(private val apiManager: APIManager) : WeatherRepository {

    private val SEOUL = "1132599"
    private val LONDON = "44418"
    private val CHICAGO = "2379574"


    override fun getSeoulWeatherData() : Single<WeatherData> {
        return apiManager.getWeather(woeid = SEOUL)
    }

    override fun getLondonWeatherData() : Single<WeatherData> {
        return apiManager.getWeather(woeid = LONDON)
    }

    override fun getChicagoWeatherData() : Single<WeatherData> {
        return apiManager.getWeather(woeid = CHICAGO)
    }
}