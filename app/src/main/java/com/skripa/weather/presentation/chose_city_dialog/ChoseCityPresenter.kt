package com.skripa.weather.presentation.chose_city_dialog

import com.arellomobile.mvp.InjectViewState
import com.skripa.weather.domain.interactor.ChoseCityInteractor
import com.skripa.weather.presentation.base.mvp.BasePresenter

@InjectViewState
class ChoseCityPresenter(
    private val interactor: ChoseCityInteractor
) : BasePresenter<ChoseCityView>(){

    /*override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getCities()
    }*/

    fun getCities(){
        interactor.getAllCity()
            .map { viewState.getCities(it) }
            .subscribe()
            .autoDisposeOnDestroy()
    }

}