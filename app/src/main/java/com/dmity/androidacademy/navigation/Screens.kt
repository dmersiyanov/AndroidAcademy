package com.dmity.androidacademy.navigation

import android.content.Context
import android.content.Intent
import com.dmity.androidacademy.feature_onboarding.view.OnBoardingActivity
import com.dmity.androidacademy.features.newsList.MainActivity

import ru.terrakok.cicerone.android.support.SupportAppScreen


class Screens {

    class MainScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context?): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    class OnBoardScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context?): Intent {
            return Intent(context, OnBoardingActivity::class.java)
        }
    }

}