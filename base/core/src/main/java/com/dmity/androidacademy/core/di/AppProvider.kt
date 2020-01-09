package com.dmity.androidacademy.core.di

import android.content.Context
import com.dmity.androidacademy.domain.system.AndroidPlatformProxy

interface AppProvider {

    fun provideContext(): Context

    fun provideAndroidPlatformProxy(): AndroidPlatformProxy
}