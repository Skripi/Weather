package com.skripa.weather.presentation.chose_city_dialog

import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.skripa.weather.R
import com.skripa.weather.domain.entity.City
import com.skripa.weather.presentation.base.dialog.BaseDialogFragment
import dagger.Lazy
import kotlinx.android.synthetic.main.dialog_chose_city.*
import javax.inject.Inject

class ChoseCityDialog: BaseDialogFragment(), ChoseCityView {

    companion object {
        lateinit var sendCityCallback: ((Double, Double) -> Unit)
    }

    @Inject
    lateinit var daggerPresenter: Lazy<ChoseCityPresenter>

    @InjectPresenter
    lateinit var presenter: ChoseCityPresenter

    @ProvidePresenter
    fun providePresenter(): ChoseCityPresenter = daggerPresenter.get()

    override val layoutRes: Int = R.layout.dialog_chose_city

    @Inject
    lateinit var adapter: ChoseCityAdapter

    override fun initView() {
        listCity.adapter = adapter
        presenter.getCities()

        //setCity()

        adapter.callback = { lat: Double, lon: Double ->
            sendCityCallback.invoke(lat, lon)
            dismiss()
        }
    }

    override fun getCities(list: List<City>) {
        adapter.addAll(list)
    }

    override fun toast(message: String, duration: Int)
            = Toast.makeText(activity, message, duration).show()

    override fun toast(resMessage: Int, duration: Int)
            = toast(getString(resMessage, duration))

}