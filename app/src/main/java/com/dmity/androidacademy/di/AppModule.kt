package com.dmity.androidacademy.di

import com.dmity.androidacademy.core.utils.AndroidPlatformProxyImpl
import com.dmity.androidacademy.domain.system.AndroidPlatformProxy
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun getAndroidPlatformProxy(injectObject: AndroidPlatformProxyImpl):
            AndroidPlatformProxy

    @Module
    companion object {

        // Пока не используется
//        private const val PREFS_NAME = "HABITS_SP"
//
//        @JvmStatic
//        @Provides
//        @Singleton
//        fun provideSharedPreferences(context: Context): SharedPreferences =
//            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}