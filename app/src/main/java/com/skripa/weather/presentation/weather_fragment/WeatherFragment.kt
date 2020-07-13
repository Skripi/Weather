package com.skripa.weather.presentation.weather_fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.location.LocationManager
import android.provider.Settings
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.jakewharton.rxbinding2.view.RxView
import com.skripa.weather.R
import com.skripa.weather.domain.entity.Weather
import com.skripa.weather.extensions.hide
import com.skripa.weather.extensions.show
import com.skripa.weather.presentation.base.fragment.BaseFragment
import com.skripa.weather.presentation.chose_city_dialog.ChoseCityDialog
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Lazy
import kotlinx.android.synthetic.main.fragment_weather.*
import javax.inject.Inject


class WeatherFragment: BaseFragment(), WeatherView {

    //constant
    private val REQUEST_CODE = 1

    override val layoutRes = R.layout.fragment_weather

    @Inject
    lateinit var daggerPresenter: Lazy<WeatherPresenter>

    @InjectPresenter
    lateinit var presenter: WeatherPresenter

    @ProvidePresenter
    fun providePresenter(): WeatherPresenter = daggerPresenter.get()

    override fun initView() {
        val manager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        presenter.checkGPS(manager)

        tvChangeCity.setOnClickListener {
            ChoseCityDialog().show(requireActivity().fragmentManager, "")
            ChoseCityDialog.sendCityCallback = { lat: Double, lon: Double ->
                presenter.getWeatherChoseCity(lat, lon)
            }
        }

        tvMyLocation.setOnClickListener { presenter.getWeather() }

        toggle.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rbCelsius -> presenter.transferTempToCelsius()
                R.id.rbFahrenheit -> presenter.transferTempToFahrenheit()
            }
        }
    }

    override fun requestPermissionsGetLocation(){
        RxPermissions(this).request(Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION).singleOrError()
            .flatMap { presenter.getMyLocation() }
            .subscribe(
                { presenter.getWeather() },
                { toast(getString(R.string.request_permissions), Toast.LENGTH_LONG) }
            )
            .autoDisposeOnDestroy()
    }

    override fun enabledGPS(){
        toast(getString(R.string.no_gps), Toast.LENGTH_LONG)
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun defaultStateRadioButton() {
        rbCelsius.isChecked = true
        tvValueTemp.text = getString(R.string.value_celsius)
    }


    @SuppressLint("SetTextI18n")
    override fun setWeather(weather: Weather) {
        tvCity.text = weather.city
        tvCurrentWeatherDegree.text = getString(R.string.current_weather, weather.temp )
        tvNameWeather.text = weather.description
        tvWind.text = getString(R.string.value_wind, weather.wind)
        tvPressure.text = getString(R.string.value_pressure, weather.pressure / 1.3333)
        tvWet.text = getString(R.string.value_wet, weather.humidity)
        tvProbablyRain.text = getString(R.string.value_probably_rain)
    }

    override fun updateTemperature(temp: Double, value: String) {
        tvCurrentWeatherDegree.text = getString(R.string.current_weather_no_sign, temp, value)
        tvValueTemp.text = value
    }

    override fun setIcon(icon: Bitmap) {
        imgWeather.setImageBitmap(icon)
    }

    override fun hideLoading() {
        fragmentWeather.show()
        progress.hide()
    }

    override fun showLoading() {
        fragmentWeather.hide()
        progress.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE && resultCode == 0){
            val provider = Settings.Secure.getString(requireActivity().contentResolver, Settings.Secure.LOCATION_PROVIDERS_ALLOWED)
            if(provider != null && provider.isNotEmpty()){
                requestPermissionsGetLocation()
            } else {
                toast(getString(R.string.no_gps), Toast.LENGTH_LONG)
                requireActivity().finish()
            }
        }
    }


}