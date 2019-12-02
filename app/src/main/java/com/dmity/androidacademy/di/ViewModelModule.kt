package com.dmity.androidacademy.di

import androidx.lifecycle.ViewModelProvider
import com.dmity.androidacademy.di.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}