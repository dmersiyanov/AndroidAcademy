package com.dmity.androidacademy.core.testing

import android.app.Application
import com.dmity.androidacademy.core.R

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_AppCompat) //or just R.style.Theme_AppCompat
    }
}