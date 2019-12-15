package com.dmity.androidacademy.core.di

import com.dmity.androidacademy.core.di.viewModel.DaggerViewModelComponent
import com.dmity.androidacademy.core.di.viewModel.ViewModelsProvider

object CoreProvidersFactory {

    fun createViewModelBuilder(): ViewModelsProvider {
        return DaggerViewModelComponent.create()
    }
}