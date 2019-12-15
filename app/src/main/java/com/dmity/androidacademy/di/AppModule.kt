package com.dmity.androidacademy.di

import dagger.Module

@Module
abstract class AppModule {

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