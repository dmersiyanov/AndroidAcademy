package com.dmity.androidacademy.features.onboarding

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dmity.androidacademy.NewsApp
import com.dmity.androidacademy.base.SubscriptionsHolder
import com.dmity.androidacademy.domain.interactor.GetOnboardingVisibilityInteractor
import com.dmity.androidacademy.features.newsList.MainActivity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    application: Application,
    private val getOnboardingVisibilityInteractor: GetOnboardingVisibilityInteractor
) :
    AndroidViewModel(application),
    SubscriptionsHolder {

    override val disposables: CompositeDisposable = CompositeDisposable()
    private var context: Context = getApplication()
    val showOnBoarding = MutableLiveData<Boolean>()

    @Inject
    lateinit var onBoardingRepo: OnBoardingRepoImpl

    init {
        NewsApp.getAppComponent().inject(this)
        setupOnBoarding()
    }

    override fun onCleared() {
        super.onCleared()
        resetCompositeDisposable()
    }

    private fun setupOnBoarding() {

        if (getOnboardingVisibilityInteractor.execute()) {

            showOnBoarding.value = true

            Completable.complete()
                .delay(DELAY_IN_SECONDS, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { MainActivity.display(context) }
                .bind()
        } else {
            showOnBoarding.value = false
            MainActivity.display(context)
        }

        onBoardingRepo.incrementCounter()

    }

    companion object {
        private const val DELAY_IN_SECONDS = 15L
    }

}