package com.dmity.androidacademy.feature_onboarding.di

import com.dmity.androidacademy.core.di.CoreProvidersFactory
import com.dmity.androidacademy.core.di.ProvidersFacade
import com.dmity.androidacademy.core.di.viewModel.ViewModelsProvider
import com.dmity.androidacademy.feature_onboarding.view.OnBoardingActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [OnboardingModule::class],
    dependencies = [ProvidersFacade::class, ViewModelsProvider::class]
)
interface OnboardingComponent :
    ViewModelsProvider, com.dmity.androidacademy.navigation.NavigationProvider {

    companion object {

        fun create(providersFacade: ProvidersFacade): OnboardingComponent {
            return DaggerOnboardingComponent
                .builder()
                .viewModelsProvider(CoreProvidersFactory.createViewModelBuilder())
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(onBoardingActivity: OnBoardingActivity)
}