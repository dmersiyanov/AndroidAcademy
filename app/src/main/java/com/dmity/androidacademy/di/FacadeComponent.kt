package com.dmity.androidacademy.di

import android.app.Application
import com.dmity.androidacademy.NewsApp
import com.dmity.androidacademy.core.di.AppProvider
import com.dmity.androidacademy.core.di.CoreProvidersFactory
import com.dmity.androidacademy.core.di.ProvidersFacade
import com.dmity.androidacademy.navigation.NavigationProvider
import dagger.Component

@Component(
    dependencies = [AppProvider::class, NavigationProvider::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .navigationProvider(CoreProvidersFactory.createNavigationBuilder())
                .build()
    }

    fun inject(app: NewsApp)
}