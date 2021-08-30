package com.test.fptestproject.data

import io.reactivex.Single


interface WeatherRepository {

    fun getSeoulWeatherData() : Single<WeatherData>

    fun getLondonWeatherData() : Single<WeatherData>

    fun getChicagoWeatherData() : Single<WeatherData>

}