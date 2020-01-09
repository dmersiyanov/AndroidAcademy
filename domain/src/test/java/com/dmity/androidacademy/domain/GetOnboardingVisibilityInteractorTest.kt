package com.dmity.androidacademy.domain

import com.dmity.androidacademy.domain.interactor.GetOnboardingVisibilityInteractor
import com.dmity.androidacademy.domain.repo.OnBoardingRepo
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetOnboardingVisibilityInteractorTest {

    @Mock
    private lateinit var onBoardingRepo: OnBoardingRepo

    private lateinit var interactor: GetOnboardingVisibilityInteractor

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        interactor = GetOnboardingVisibilityInteractor(onBoardingRepo)
    }

    @Test
    fun `when onboarding counter is odd - false returned`() {

        whenever(onBoardingRepo.getCounter()).doReturn(3)

        assertFalse(interactor.execute())

    }

    @Test
    fun `when onboarding counter is even - true returned`() {

        whenever(onBoardingRepo.getCounter()).doReturn(2)

        assertTrue(interactor.execute())

    }
}