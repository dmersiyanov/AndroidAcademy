package com.dmity.androidacademy.core.testing

import android.app.Application
import android.content.Context
import com.dmity.androidacademy.core.di.AppWithFacade
import com.dmity.androidacademy.core.di.ProvidersFacade
import com.dmity.androidacademy.core.utils.AndroidPlatformProxyImpl
import com.dmity.androidacademy.domain.system.AndroidPlatformProxy

class TestApplication : Application(), AppWithFacade {

    override fun getFacade(): ProvidersFacade {
        return object : ProvidersFacade {
            override fun provideContext(): Context {
                return this@TestApplication
            }

            override fun provideAndroidPlatformProxy(): AndroidPlatformProxy {
                return AndroidPlatformProxyImpl(this@TestApplication)
            }
        }
    }
}