package com.skripa.weather.domain.service

interface ConverterTempService {

    fun convertInCelsius(temperature: Double): Double

    fun convertInFahrenheit(temperature: Double): Double

}