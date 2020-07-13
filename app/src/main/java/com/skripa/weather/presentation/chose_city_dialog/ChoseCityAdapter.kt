package com.skripa.weather.presentation.chose_city_dialog

import android.view.ViewGroup
import com.skripa.weather.R
import com.skripa.weather.domain.entity.City
import com.skripa.weather.extensions.inflate
import com.skripa.weather.presentation.base.adapter.RecyclerAdapter

class ChoseCityAdapter: RecyclerAdapter<City, ChoseCityHolder>() {

    lateinit var callback: ((Double, Double) -> Unit)

    override fun onCreateHolder(viewGroup: ViewGroup, viewType: Int): ChoseCityHolder {
        return ChoseCityHolder(viewGroup.inflate(R.layout.item_city))
    }

    override fun onBindHolder(holder: ChoseCityHolder, item: City, pos: Int) {
        holder.apply {
            tvNameCity.text = item.name

            itemView.setOnClickListener {
                callback.invoke(item.lat, item.lon)
            }
        }
    }
}