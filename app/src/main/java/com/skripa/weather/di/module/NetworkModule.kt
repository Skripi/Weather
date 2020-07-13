package com.skripa.weather.di.module

import com.google.gson.Gson
import com.skripa.weather.data.server.WeatherService
import com.skripa.weather.util.Const
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(WeatherService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .readTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

    @Provides
    @Singleton
    fun provideGson()
            = Gson()


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}
