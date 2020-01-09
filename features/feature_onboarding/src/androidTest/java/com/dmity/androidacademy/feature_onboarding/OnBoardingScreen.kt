package com.dmity.androidacademy.feature_onboarding

import com.agoda.kakao.pager.KViewPager
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton

class OnBoardingScreen : Screen<OnBoardingScreen>() {
    val viewpager =
        KViewPager { withId(R.id.viewpager) }
    val btnSkip =
        KButton { withId(R.id.btnSkip) }
}