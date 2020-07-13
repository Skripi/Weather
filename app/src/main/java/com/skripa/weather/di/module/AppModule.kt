package com.skripa.weather.di.module

import com.skripa.weather.di.WeatherApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: WeatherApplication)
            = application.applicationContext!!

}