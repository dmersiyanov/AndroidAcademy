package com.dmity.androidacademy.feature_onboarding.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmity.androidacademy.core.SubscriptionsHolder
import com.dmity.androidacademy.domain.interactor.GetOnboardingVisibilityInteractor
import com.dmity.androidacademy.domain.repo.OnBoardingRepo
import com.dmity.androidacademy.domain.system.AndroidPlatformProxy
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    private val getOnboardingVisibilityInteractor: GetOnboardingVisibilityInteractor,
    private val onBoardingRepo: OnBoardingRepo,
    private val androidPlatformProxy: AndroidPlatformProxy
) :
    ViewModel(),
    SubscriptionsHolder {

    override val disposables: CompositeDisposable = CompositeDisposable()
    val showOnBoarding = MutableLiveData<Boolean>()

    init {
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
                .subscribe {
                    androidPlatformProxy.showToast("Навигация на MainActivity")
//                    MainActivity.display(context)
                }
                .bind()
        } else {
            showOnBoarding.value = false
            androidPlatformProxy.showToast("Навигация на MainActivity")
//            MainActivity.display(context)
        }

        onBoardingRepo.incrementCounter()

    }

    companion object {
        private const val DELAY_IN_SECONDS = 15L
    }

}