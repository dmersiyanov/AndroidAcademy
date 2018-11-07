package com.dmity.androidacademy

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import io.reactivex.plugins.RxJavaPlugins


class NewsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        setGlobalRxJavaErrorHandler()
    }


    @SuppressLint("LongLogTag")
    private fun setGlobalRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler { e ->
            if (e is NullPointerException || e is IllegalArgumentException) {
                // that's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), e)
            }
            if (e is IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), e)
            }

            Log.w("Undeliverable exception received", e)
        }
    }

}