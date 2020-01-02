package com.dmity.androidacademy.feature_onboarding.di

import androidx.lifecycle.ViewModel
import com.dmity.androidacademy.domain.interactor.GetOnboardingVisibilityInteractor
import com.dmity.androidacademy.domain.repo.OnBoardingRepo
import com.dmity.androidacademy.domain.system.AndroidPlatformProxy
import com.dmity.androidacademy.feature_onboarding.repo.OnBoardingRepoImpl
import com.dmity.androidacademy.feature_onboarding.viewModel.OnBoardingViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class OnboardingModule {

    @Binds
    @Singleton
    abstract fun bindsBoardingRepo(onBoardingRepo: OnBoardingRepoImpl): OnBoardingRepo


    @Module
    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun provideOnBoardingViewModel(
            map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>,
            getOnboardingVisibilityInteractor: GetOnboardingVisibilityInteractor,
            onBoardingRepo: OnBoardingRepo,
            androidPlatformProxy: AndroidPlatformProxy
        ): ViewModel = OnBoardingViewModel(
            getOnboardingVisibilityInteractor = getOnboardingVisibilityInteractor,
            onBoardingRepo = onBoardingRepo,
            androidPlatformProxy = androidPlatformProxy
        ).also {
            map[OnBoardingViewModel::class.java] = it
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideDummy(viewModel: ViewModel) = EagerTrigger()
    }
}

class EagerTrigger