package com.dmity.androidacademy.core.di

import android.content.Context

interface AppProvider {

    fun provideContext(): Context
}