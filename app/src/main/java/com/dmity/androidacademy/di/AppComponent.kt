package com.dmity.androidacademy.di

import com.dmity.androidacademy.features.onboarding.OnBoardingActivity
import com.dmity.androidacademy.features.onboarding.OnBoardingViewModel
import com.dmity.androidacademy.features.onboarding.di.OnBoardingModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        OnBoardingModule::class,
        ViewModelModule::class]
)
interface AppComponent {

    fun inject(onBoardingViewModel: OnBoardingViewModel)
    fun inject(onBoardingActivity: OnBoardingActivity)

}