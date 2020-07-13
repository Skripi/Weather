package com.skripa.weather.domain.interactor

import com.skripa.weather.domain.repository.WeatherRepository
import com.skripa.weather.domain.service.LocationService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherInteractor(
    private val weatherRepository: WeatherRepository,
    private val locationService: LocationService
) {

    fun getWeatherAnotherCity(lat:Double, lon:Double ) =
        weatherRepository.get(lat,lon)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getWeatherMyCity() =
        locationService.get()
            .flatMap { weatherRepository.get(it.lat, it.lon) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getIconWeatherMyCity() =
        getWeatherMyCity()
            .flatMap { weatherRepository.getIcon(it.icon) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getIconWeatherAnotherCity(lat:Double, lon:Double ) =
        getWeatherAnotherCity(lat, lon)
            .flatMap { weatherRepository.getIcon(it.icon) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}