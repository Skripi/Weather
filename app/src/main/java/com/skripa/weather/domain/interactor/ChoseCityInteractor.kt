package com.skripa.weather.domain.interactor

import com.skripa.weather.domain.repository.CityRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChoseCityInteractor(
    private val cityRepository: CityRepository
) {

    fun getAllCity() =
        cityRepository.getCity()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

}