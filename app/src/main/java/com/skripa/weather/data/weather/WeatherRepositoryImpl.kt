package com.skripa.weather.data.weather

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.skripa.weather.data.server.WeatherService
import com.skripa.weather.data.server.response.WeatherResponse
import com.skripa.weather.domain.entity.Weather
import com.skripa.weather.domain.repository.WeatherRepository
import com.skripa.weather.util.Const
import com.skripa.weather.util.Mapper
import io.reactivex.Single

class WeatherRepositoryImpl(
    private val service: WeatherService,
    private val mapper: Mapper<WeatherResponse, Weather>
): WeatherRepository {

    override fun get(lat: Double, lon: Double): Single<Weather> =
        service.getWeather(lat, lon, Const.APPID )
            .map { mapper.transform(it) }

    override fun getIcon(icon: String): Single<Bitmap> =
        service.getWeatherImg("https://openweathermap.org/img/wn/${icon}.png")
            .map { BitmapFactory.decodeStream(it.byteStream()) }
}