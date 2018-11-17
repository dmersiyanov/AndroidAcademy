package com.dmity.androidacademy.features.onboarding


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dmity.androidacademy.R
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.base.Layout

@Layout(R.layout.activity_onboarding)
class OnBoardingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(OnBoardingViewModel::class.java)
        viewModel.showOnBoarding.observe(this, Observer { showOnBoarding ->
            if (showOnBoarding) {
                setContentView(R.layout.activity_onboarding)
            }
        })

    }

}
