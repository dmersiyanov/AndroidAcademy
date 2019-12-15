package com.dmity.androidacademy.features.onboarding.di

import androidx.lifecycle.ViewModel
import com.dmity.androidacademy.di.utils.ViewModelKey
import com.dmity.androidacademy.domain.repo.OnBoardingRepo
import com.dmity.androidacademy.features.onboarding.OnBoardingRepoImpl
import com.dmity.androidacademy.features.onboarding.OnBoardingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class OnBoardingModule {

    @Singleton
    @Binds
    abstract fun getOnBoardingRepo(onBoardingRepoImpl: OnBoardingRepoImpl): OnBoardingRepo

    @IntoMap
    @ViewModelKey(OnBoardingViewModel::class)
    @Binds
    abstract fun bindOnBoardingViewModel(vm: OnBoardingViewModel): ViewModel

}