package com.test.fptestproject.data

import com.google.gson.annotations.SerializedName
import java.util.*
import javax.inject.Singleton


@Singleton
interface ListData {
        companion object {
                val TYPE_HEADER: Int = 0
                val TYPE_ITEM: Int = 1
        }
        fun getItemType(): Int
}

data class WeatherData(

        @SerializedName("title")
        val title: String,
        @SerializedName("consolidated_weather")
        val consolidated_weather: ArrayList<ConsolidatedWeatherData>

): ListData {
        override fun getItemType(): Int {
                return 0
        }
}

data class ConsolidatedWeatherData(
        val isHeaderView: Boolean,
        val title: String,

        @SerializedName("id")
        val id: String,
        @SerializedName("applicable_date")
        val applicable_date: String,
        @SerializedName("min_temp")
        val min_temp: Float,
        @SerializedName("max_temp")
        val max_temp: Float,
        @SerializedName("weather_state_name")
        val weather_state_name: String,
        @SerializedName("weather_state_abbr")
        val weather_state_abbr: String
): ListData {
        override fun getItemType(): Int {
                return 1
        }
}

