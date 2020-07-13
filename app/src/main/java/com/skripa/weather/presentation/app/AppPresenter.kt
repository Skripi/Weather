package com.skripa.weather.presentation.app

import com.arellomobile.mvp.InjectViewState
import com.skripa.weather.presentation.Screens
import com.skripa.weather.presentation.base.mvp.BasePresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class AppPresenter(
    private val router: Router
) : BasePresenter<AppView>() {

    fun beginWork(){
        router.newRootChain(Screens.WeatherScreen)
    }
}