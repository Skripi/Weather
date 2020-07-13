package com.skripa.weather.presentation.base.activity

import android.os.Bundle
import androidx.moxy.MvpAppCompatActivity
import com.skripa.weather.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity(), HasAndroidInjector {

    protected abstract val layoutRes: Int

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    protected val navigator: Navigator = SupportAppNavigator(this, R.id.mainContainer)

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        initView()
    }

    protected abstract fun initView()

    override fun onResumeFragments() {
        navigatorHolder.setNavigator(navigator)
        super.onResumeFragments()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}