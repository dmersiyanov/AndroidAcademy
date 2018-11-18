package com.dmity.androidacademy.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity: AppCompatActivity(), SubscriptionsHolder {

    override val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        initUi()
        initUi(savedInstanceState)
        initUx()
    }

    override fun onDestroy() {
        resetCompositeDisposable()
        super.onDestroy()
    }

    open fun initUx() {}
    open fun initUi() {}
    open fun initUi(savedInstanceState: Bundle?) {}
    open fun showProgress(show: Boolean) {}
    open fun showError(errorMessage: String = "", show: Boolean) {}

    private fun initLayout(){
        var layoutId = 0
        val annotation = javaClass.getAnnotation(Layout::class.java)
        if (annotation != null) {
            layoutId = annotation.id
        }

        if (layoutId != 0) {
            setContentView(layoutId)
        }
    }


}