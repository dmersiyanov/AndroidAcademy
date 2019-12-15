package com.dmity.androidacademy.feature_onboarding.viewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dmity.androidacademy.core.SubscriptionsHolder
import com.dmity.androidacademy.domain.interactor.GetOnboardingVisibilityInteractor
import com.dmity.androidacademy.domain.repo.OnBoardingRepo
import com.dmity.androidacademy.navigation.AppRouter
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    application: Application,
    private val getOnboardingVisibilityInteractor: GetOnboardingVisibilityInteractor,
    private val onBoardingRepo: OnBoardingRepo,
    private val appRouter: Router
) :
    AndroidViewModel(application),
    SubscriptionsHolder {

    override val disposables: CompositeDisposable = CompositeDisposable()
    private var context: Context = getApplication()
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
                    Toast.makeText(context, "Навигация на MainActivity", Toast.LENGTH_LONG).show()
                    // TODO add feature navigation
//                    MainActivity.display(context)
                }
                .bind()
        } else {
            showOnBoarding.value = false
            Toast.makeText(context, "Навигация на MainActivity", Toast.LENGTH_LONG).show()
            // TODO add feature navigation
//            MainActivity.display(context)
        }

        onBoardingRepo.incrementCounter()

    }

    companion object {
        private const val DELAY_IN_SECONDS = 15L
    }

}