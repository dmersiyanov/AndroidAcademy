package com.dmity.androidacademy.feature_onboarding

import com.dmity.androidacademy.feature_onboarding.view.OnBoardingActivity
import kotlinx.android.synthetic.main.activity_onboarding.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class OnBoardingActivityTest {

    private lateinit var activityController: ActivityController<OnBoardingActivity>
    private lateinit var activity: OnBoardingActivity

    @Before
    fun setup() {
        activityController = Robolectric.buildActivity(OnBoardingActivity::class.java).setup()
        activity = activityController.get()
    }

    @Test
    fun checkIfAdapterHasTwoTabs() {
        assertThat(activity.viewpager.adapter?.count, equalTo(2))
    }

}