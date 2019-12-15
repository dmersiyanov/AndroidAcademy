package com.dmity.androidacademy.di

import android.app.Application
import com.dmity.androidacademy.NewsApp
import com.dmity.androidacademy.core.di.AppProvider
import com.dmity.androidacademy.core.di.ProvidersFacade
import dagger.Component

@Component(
    dependencies = [AppProvider::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .build()
    }

    fun inject(app: NewsApp)
}