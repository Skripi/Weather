package com.skripa.weather.domain.service

import com.skripa.weather.domain.entity.City
import io.reactivex.Single

interface LocationService {

    fun get(): Single<City>

}