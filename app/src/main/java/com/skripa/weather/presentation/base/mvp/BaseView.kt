package com.skripa.weather.presentation.base.mvp

import android.widget.Toast
import androidx.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface BaseView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun toast(message: String, duration: Int = Toast.LENGTH_SHORT)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun toast(@StringRes resMessage: Int, duration: Int = Toast.LENGTH_SHORT)

}