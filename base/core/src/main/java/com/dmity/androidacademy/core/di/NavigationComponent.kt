package com.dmity.androidacademy.core.di

import com.dmity.androidacademy.navigation.di.NavigationModule
import com.dmity.androidacademy.navigation.di.NavigationProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NavigationModule::class]
)
interface NavigationComponent : NavigationProvider