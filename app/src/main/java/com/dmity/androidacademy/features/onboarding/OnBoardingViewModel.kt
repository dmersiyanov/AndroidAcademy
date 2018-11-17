package com.dmity.androidacademy.features.onboarding

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dmity.androidacademy.base.SubscriptionsHolder
import com.dmity.androidacademy.features.newsList.MainActivity
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class OnBoardingViewModel(application: Application): AndroidViewModel(application), SubscriptionsHolder {

    override val disposables: CompositeDisposable = CompositeDisposable()
    private var context: Context = getApplication()
    private var counter = 0
    val showOnBoarding = MutableLiveData<Boolean>()

    private var onBoardingRepo: OnBoardingRepo

    init {
        onBoardingRepo = OnBoardingRepo(context)
        setupOnBoarding()
    }

    override fun onCleared() {
        super.onCleared()
        resetCompositeDisposable()
    }

    private fun setupOnBoarding() {

        counter = onBoardingRepo.getCounter()

        if (counter % 2 == 0) {

            showOnBoarding.value = true

            Completable.complete()
                    .delay(DELAY_IN_SECONDS, TimeUnit.SECONDS)
                    .subscribe { MainActivity.display(context)}
                    .bind()
        } else {
            showOnBoarding.value = false
            MainActivity.display(context)
        }

        onBoardingRepo.incrementCounter()

    }

    companion object {
        private const val DELAY_IN_SECONDS = 3L
    }

}