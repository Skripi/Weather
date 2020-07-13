package com.skripa.weather.presentation.chose_city_dialog

import com.skripa.weather.domain.interactor.ChoseCityInteractor
import com.skripa.weather.domain.repository.CityRepository
import dagger.Module
import dagger.Provides

@Module
class ChoseCityModule {

    @Provides
    fun provideAdapter(): ChoseCityAdapter =
        ChoseCityAdapter()

    @Provides
    fun providePresenter(interactor: ChoseCityInteractor) :
            ChoseCityPresenter = ChoseCityPresenter(interactor)

    @Provides
    fun provideInteractor(cityRepository: CityRepository): ChoseCityInteractor =
        ChoseCityInteractor(cityRepository)

}