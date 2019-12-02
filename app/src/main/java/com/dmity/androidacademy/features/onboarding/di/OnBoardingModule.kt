package com.dmity.androidacademy.features.onboarding.di

import com.dmity.androidacademy.domain.repo.OnBoardingRepo
import com.dmity.androidacademy.features.onboarding.OnBoardingRepoImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class OnBoardingModule {

    @Singleton
    @Binds
    abstract fun getOnBoardingRepo(onBoardingRepoImpl: OnBoardingRepoImpl): OnBoardingRepo
}