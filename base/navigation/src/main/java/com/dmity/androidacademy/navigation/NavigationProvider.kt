package com.dmity.androidacademy.navigation

import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router


interface NavigationProvider {

    fun provideRouter(): Router

//    fun provideAppRouter(): AppRouter

    fun provideNavigatorHolder(): NavigatorHolder

}