package com.skripa.weather.data.server.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main") val main: Main,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("name") val name: String
){


    data class Weather(
        @SerializedName("id") val id: Long,
        @SerializedName("main") val main: String,
        @SerializedName("description") val description: String,
        @SerializedName("icon") val icon: String
    )

    data class Main(
        @SerializedName("temp") val temp: Double,
        @SerializedName("feels_like") val feelsLike: Double,
        @SerializedName("pressure") val pressure: Double,
        @SerializedName("humidity") val humidity: Double
    )

    data class Wind(
        @SerializedName("speed") val speed: Double,
        @SerializedName("deg") val deg: Double
    )
}