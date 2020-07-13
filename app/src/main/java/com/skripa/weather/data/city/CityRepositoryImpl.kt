package com.skripa.weather.data.city

import com.skripa.weather.domain.entity.City
import com.skripa.weather.domain.repository.CityRepository
import io.reactivex.Single

class CityRepositoryImpl(): CityRepository {
    override fun getCity(): Single<List<City>> =
        Single.just(
            listOf(
                City(54.950443, 73.424466, "Омск"),
                City( 55.7558, 37.6173, "Москва"),
                City( 54.70649, 20.51095, "Калининград"),
                City( 56.8431, 60.6454,"Екатеринбург"),
                City( 43.59917, 39.72569, "Сочи"),
                City( 55.0415, 82.9346, "Новосибирск"),
                City( 47.233334, 39.700001, "Ростов-на-Дону")
            )
        )
}