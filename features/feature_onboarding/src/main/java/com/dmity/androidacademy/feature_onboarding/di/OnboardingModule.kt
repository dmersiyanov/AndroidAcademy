package com.dmity.androidacademy.feature_onboarding.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.dmity.androidacademy.domain.interactor.GetOnboardingVisibilityInteractor
import com.dmity.androidacademy.domain.repo.OnBoardingRepo
import com.dmity.androidacademy.feature_onboarding.repo.OnBoardingRepoImpl
import com.dmity.androidacademy.feature_onboarding.viewModel.OnBoardingViewModel
import com.dmity.androidacademy.navigation.AppRouter
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router
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
            context: Context,
            onBoardingRepo: OnBoardingRepo,
            appRouter: Router
        ): ViewModel = OnBoardingViewModel(
            application = context as Application,
            getOnboardingVisibilityInteractor = getOnboardingVisibilityInteractor,
            onBoardingRepo = onBoardingRepo,
            appRouter = appRouter
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