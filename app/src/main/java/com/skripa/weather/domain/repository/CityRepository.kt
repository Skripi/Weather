package com.skripa.weather.domain.repository

import com.skripa.weather.domain.entity.City
import io.reactivex.Single

interface CityRepository {

    fun getCity(): Single<List<City>>

}