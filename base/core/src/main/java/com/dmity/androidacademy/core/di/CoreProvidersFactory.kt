package com.dmity.androidacademy.core.di

import com.dmity.androidacademy.core.di.viewModel.DaggerViewModelComponent
import com.dmity.androidacademy.core.di.viewModel.ViewModelsProvider
import com.dmity.androidacademy.navigation.di.NavigationProvider

object CoreProvidersFactory {

    fun createViewModelBuilder(): ViewModelsProvider {
        return DaggerViewModelComponent.create()
    }

    fun createNavigationBuilder(): NavigationProvider {
        return DaggerNavigationComponent.create()
    }
}