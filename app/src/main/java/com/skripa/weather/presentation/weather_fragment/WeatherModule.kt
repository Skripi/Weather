package com.skripa.weather.presentation.weather_fragment

import com.skripa.weather.domain.interactor.WeatherInteractor
import com.skripa.weather.domain.repository.WeatherRepository
import com.skripa.weather.domain.service.ConverterTempService
import com.skripa.weather.domain.service.LocationService
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class WeatherModule {

    @Provides
    fun providePresenter(
        locationService: LocationService,
        interactor: WeatherInteractor,
        converterTempService: ConverterTempService
    ): WeatherPresenter
            = WeatherPresenter( locationService, interactor, converterTempService)

    @Provides
    fun provideInteractor(weatherRepository: WeatherRepository,
                          locationService: LocationService): WeatherInteractor
            = WeatherInteractor(weatherRepository, locationService)
}