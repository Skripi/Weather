package com.skripa.weather.util

interface Mapper<FROM, TO> {
    fun transform(value: FROM): TO
}
