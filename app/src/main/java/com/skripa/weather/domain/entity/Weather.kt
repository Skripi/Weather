package com.skripa.weather.domain.entity

data class Weather(
    val city: String,
    val temp: Double,
    val description: String,
    val wind: Double,
    val pressure: Double,
    val humidity: Double,
    val icon: String
){}