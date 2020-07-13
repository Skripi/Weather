package com.skripa.weather.data.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.skripa.weather.domain.entity.City
import com.skripa.weather.domain.service.LocationService
import io.reactivex.Single

class LocationServiceImpl(
    private var context: Context,
    private var fusedLocationClient: FusedLocationProviderClient
): LocationService {

    override fun get(): Single<City> =
        Single.create { single ->
            if( !checkPermission()){
                fusedLocationClient.lastLocation
                    .addOnSuccessListener {
                        if (it != null)
                            single.onSuccess(City(lat = it.latitude, lon = it.longitude))
                    }
                    .addOnFailureListener { single.onError(it) }
            }
        }

    private fun checkPermission() =
        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED

}

