package com.dmity.androidacademy.feature_onboarding

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.dmity.androidacademy.feature_onboarding.view.OnBoardingActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class OnBoardingActivityKakaoTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(OnBoardingActivity::class.java)

    val screen = OnBoardingScreen()


    @Test
    fun isContentShowing() {
        screen {
            btnSkip {
                isVisible()
            }
        }
    }
}


