package com.skripa.weather.di.module

import com.skripa.weather.presentation.app.AppActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun bindAppActivity(): AppActivity

}
