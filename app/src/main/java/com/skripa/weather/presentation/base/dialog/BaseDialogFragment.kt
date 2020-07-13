package com.skripa.weather.presentation.base.dialog

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpDialogFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject


abstract class BaseDialogFragment: MvpDialogFragment(), HasAndroidInjector {

    protected abstract val layoutRes: Int

    protected abstract fun initView()

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreateView(inflater: LayoutInflater,
                              viewGroup: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(layoutRes, viewGroup, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
    }

    override fun onAttach(activity: Activity) {
        AndroidInjection.inject(this)
        super.onAttach(activity)
    }

    private val autoDisposableOnPause = CompositeDisposable()
    private val autoDisposableOnStop = CompositeDisposable()
    private val autoDisposableOnDestroy = CompositeDisposable()

    protected fun Disposable.autoDisposeOnPause(): Disposable {
        autoDisposableOnPause += this
        return this
    }

    protected fun Disposable.autoDisposeOnStop(): Disposable {
        autoDisposableOnStop += this
        return this
    }

    protected fun Disposable.autoDisposeOnDestroy(): Disposable {
        autoDisposableOnDestroy += this
        return this
    }

    override fun onPause() {
        super.onPause()
        // use clear() instead of dispose to able reuse the same object again
        autoDisposableOnPause.clear()
    }

    override fun onStop() {
        super.onStop()
        // use clear() instead of dispose to able reuse the same object again
        autoDisposableOnStop.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        autoDisposableOnDestroy.dispose()
    }

}