package com.test.fptestproject.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface APIManager {
    @GET("/api/location/{woeid}")
    fun getWeather(
        @Path("woeid") woeid: String
    ) : Single<WeatherData>

}