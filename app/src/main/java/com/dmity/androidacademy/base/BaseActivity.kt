package com.dmity.androidacademy.base

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity: AppCompatActivity(), SubscriptionsHolder {

    override val mDisposable: CompositeDisposable = CompositeDisposable()

    open fun initUx() {}
    open fun initUi() {}

    override fun onDestroy() {
        super.onDestroy()
        resetCompositeDisposable()
    }

}