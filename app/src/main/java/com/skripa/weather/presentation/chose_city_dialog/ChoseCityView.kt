package com.skripa.weather.presentation.chose_city_dialog

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.skripa.weather.domain.entity.City
import com.skripa.weather.presentation.base.mvp.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface ChoseCityView: BaseView {

    fun getCities(list: List<City>)

}