package com.dmity.androidacademy.base

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity: AppCompatActivity(), SubscriptionsHolder {

    override val disposables: CompositeDisposable = CompositeDisposable()

    open fun initUx() {}
    open fun initUi() {}
    open fun showProgress(show: Boolean) {}
    open fun showError(errorMessage: String, show: Boolean) {}

    override fun onDestroy() {
        resetCompositeDisposable()
        super.onDestroy()
    }

}