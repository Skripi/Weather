package com.skripa.weather.di.module

import com.skripa.weather.data.server.response.WeatherResponse
import com.skripa.weather.data.weather.WeatherMapper
import com.skripa.weather.domain.entity.Weather
import com.skripa.weather.util.Mapper
import dagger.Module
import dagger.Provides

@Module
class MappersModule {

    @Provides
    fun provideFaqMapper(): Mapper<WeatherResponse, Weather> =
        WeatherMapper()

}