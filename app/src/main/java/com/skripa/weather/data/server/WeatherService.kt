package com.skripa.weather.data.server

import com.skripa.weather.data.server.response.WeatherResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface WeatherService {

    @GET("/data/2.5/weather")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("APPID") appid: String,
        @Query("lang") lang: String = "ru"
    ): Single<WeatherResponse>

    @GET
    fun getWeatherImg(@Url url: String): Single<ResponseBody>
}