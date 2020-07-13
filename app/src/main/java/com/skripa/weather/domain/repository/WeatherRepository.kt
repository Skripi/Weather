package com.skripa.weather.domain.repository

import android.graphics.Bitmap
import com.skripa.weather.domain.entity.Weather
import io.reactivex.Single

interface WeatherRepository {

    fun get(lat: Double, lon: Double): Single<Weather>

    fun getIcon(icon: String): Single<Bitmap>

}