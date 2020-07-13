package com.skripa.weather.presentation

import androidx.fragment.app.Fragment
import com.skripa.weather.presentation.weather_fragment.WeatherFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object WeatherScreen : SupportAppScreen() {
        override fun getFragment(): Fragment
            = WeatherFragment()
    }
}