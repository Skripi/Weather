package com.skripa.weather.data.weather

import com.skripa.weather.data.server.response.WeatherResponse
import com.skripa.weather.domain.entity.Weather
import com.skripa.weather.util.Const
import com.skripa.weather.util.Mapper

class WeatherMapper : Mapper<WeatherResponse, Weather>{
    override fun transform(value: WeatherResponse): Weather =
        Weather(
            city = value.name,
            temp = value.main.temp - Const.KELVIN,
            description = value.weather.first().description,
            wind = value.wind.speed,
            pressure = value.main.pressure,
            humidity = value.main.humidity,
            icon = value.weather.first().icon
        )


}
