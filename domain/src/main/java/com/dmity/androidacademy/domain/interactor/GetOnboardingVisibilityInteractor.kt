package com.dmity.androidacademy.domain.interactor

import com.dmity.androidacademy.domain.repo.OnBoardingRepo
import javax.inject.Inject

/** Показываем онбординг через раз */
class GetOnboardingVisibilityInteractor @Inject constructor(
    private val onBoardingRepo: OnBoardingRepo
) {

    fun execute(): Boolean {
        return onBoardingRepo.getCounter() % 2 == 0
    }

}