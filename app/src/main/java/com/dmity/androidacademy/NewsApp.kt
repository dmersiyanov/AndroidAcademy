package com.dmity.androidacademy

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.dmity.androidacademy.core.di.AppWithFacade
import com.dmity.androidacademy.core.di.ProvidersFacade
import com.dmity.androidacademy.di.FacadeComponent
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber


class NewsApp : Application(), AppWithFacade {

    override fun onCreate() {
        super.onCreate()
        context = this

        (getFacade() as FacadeComponent).inject(this)

        setGlobalRxJavaErrorHandler()
    }

    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(this).also {
            facadeComponent = it
        }
    }

    @SuppressLint("LongLogTag")
    private fun setGlobalRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler { e ->
            if (e is NullPointerException || e is IllegalArgumentException) {
                // that's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), e)
            }
            if (e is IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), e)
            }

            Timber.w(e)
        }
    }

    companion object {

        private var facadeComponent: FacadeComponent? = null

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }

}