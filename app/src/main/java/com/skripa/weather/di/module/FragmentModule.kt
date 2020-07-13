package com.skripa.weather.di.module

import com.skripa.weather.presentation.chose_city_dialog.ChoseCityDialog
import com.skripa.weather.presentation.chose_city_dialog.ChoseCityModule
import com.skripa.weather.presentation.weather_fragment.WeatherFragment
import com.skripa.weather.presentation.weather_fragment.WeatherModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector(modules = [WeatherModule::class])
    abstract fun bindWeatherFragment(): WeatherFragment

    @ContributesAndroidInjector(modules = [ChoseCityModule::class])
    abstract fun bindChoseCityDialog(): ChoseCityDialog

}