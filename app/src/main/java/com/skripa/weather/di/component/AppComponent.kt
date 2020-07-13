package com.skripa.weather.di.component

import com.skripa.weather.di.WeatherApplication
import com.skripa.weather.di.module.*
import com.skripa.weather.presentation.app.AppScreenModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,

    AppModule::class,
    NavigationModule::class,
    ActivityBuilder::class,
    RepositoryModule::class,
    NetworkModule::class,
    AppScreenModule::class,
    MappersModule::class

])
interface AppComponent : AndroidInjector <WeatherApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: WeatherApplication): Builder

        fun build(): AppComponent
    }
}