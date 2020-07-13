package com.skripa.weather.presentation.app

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class AppScreenModule {

    @Provides
    fun providePresenter(router: Router): AppPresenter
            = AppPresenter(router)

}