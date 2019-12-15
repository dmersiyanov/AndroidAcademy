package com.dmity.androidacademy.core.di.viewModel

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface ViewModelComponent :
    ViewModelsProvider