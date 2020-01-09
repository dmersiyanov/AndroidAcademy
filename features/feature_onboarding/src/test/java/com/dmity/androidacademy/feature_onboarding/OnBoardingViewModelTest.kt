package com.dmity.androidacademy.feature_onboarding

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.dmity.androidacademy.core.rules.RxImmediateSchedulerRule
import com.dmity.androidacademy.domain.interactor.GetOnboardingVisibilityInteractor
import com.dmity.androidacademy.feature_onboarding.viewModel.OnBoardingViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

// TODO Test navigation when it's implemented
class OnBoardingViewModelTest {

    @Rule
    @JvmField
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private var getOnboardingVisibilityInteractor: GetOnboardingVisibilityInteractor = mock()

    private lateinit var viewModel: OnBoardingViewModel

    @Mock
    lateinit var observer: Observer<Boolean>

    @Mock
    lateinit var lifecycleOwner: LifecycleOwner

    lateinit var lifecycle: Lifecycle

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lifecycle = LifecycleRegistry(lifecycleOwner)
        viewModel = OnBoardingViewModel(getOnboardingVisibilityInteractor, mock())
        viewModel.showOnBoarding.observeForever(observer)
    }

    @Test
    fun `when getOnboardingVisibilityInteractor returned true - liveData set to true`() {
        whenever(getOnboardingVisibilityInteractor.execute()).doReturn(true)

        viewModel.setupOnBoarding()

        verify(observer).onChanged(true)

    }

    @Test
    fun `when getOnboardingVisibilityInteractor returned false - liveData set to false`() {
        whenever(getOnboardingVisibilityInteractor.execute()).doReturn(false)

        viewModel.setupOnBoarding()

        verify(observer).onChanged(false)

    }

    companion object {
        @JvmField
        @ClassRule
        val rule = RxImmediateSchedulerRule()
    }


}