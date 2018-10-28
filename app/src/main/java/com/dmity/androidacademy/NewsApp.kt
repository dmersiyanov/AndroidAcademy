package com.dmity.androidacademy

import android.app.Application
import android.util.Log
import io.reactivex.plugins.RxJavaPlugins

class NewsApp: Application() {


    override fun onCreate() {

        RxJavaPlugins.setErrorHandler { Log.e("RxJavaPlugins.onError", it.message) }

        super.onCreate()
    }

}