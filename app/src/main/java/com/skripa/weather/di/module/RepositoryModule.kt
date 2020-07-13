package com.skripa.weather.di.module

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.skripa.weather.data.city.CityRepositoryImpl
import com.skripa.weather.data.location.LocationServiceImpl
import com.skripa.weather.data.server.WeatherService
import com.skripa.weather.data.server.response.WeatherResponse
import com.skripa.weather.data.weather.ConverterTempServiceImpl
import com.skripa.weather.data.weather.WeatherRepositoryImpl
import com.skripa.weather.domain.entity.Weather
import com.skripa.weather.domain.repository.CityRepository
import com.skripa.weather.domain.repository.WeatherRepository
import com.skripa.weather.domain.service.ConverterTempService
import com.skripa.weather.domain.service.LocationService
import com.skripa.weather.util.Mapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLocationServiceImpl(
        context: Context
    ): LocationService =
        LocationServiceImpl(
            context,
            LocationServices.getFusedLocationProviderClient(context)
        )

    @Provides
    @Singleton
    fun provideConverterTempServiceImpl(): ConverterTempService =
        ConverterTempServiceImpl()

    @Provides
    @Singleton
    fun provideCityRepositoryImpl(): CityRepository =
        CityRepositoryImpl()

    @Provides
    @Singleton
    fun provideWeatherRepository(
         service: WeatherService,
         mapper: Mapper<WeatherResponse, Weather>
    ): WeatherRepository =
        WeatherRepositoryImpl(
            service, mapper
        )

}