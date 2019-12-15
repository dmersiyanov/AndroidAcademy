package com.dmity.androidacademy.feature_onboarding.view


import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dmity.androidacademy.core.BaseActivity
import com.dmity.androidacademy.core.di.AppWithFacade
import com.dmity.androidacademy.feature_onboarding.R
import com.dmity.androidacademy.feature_onboarding.di.EagerTrigger
import com.dmity.androidacademy.feature_onboarding.di.OnboardingComponent
import com.dmity.androidacademy.feature_onboarding.viewModel.OnBoardingViewModel
import kotlinx.android.synthetic.main.activity_onboarding.*
import javax.inject.Inject

class OnBoardingActivity : BaseActivity(layoutId = R.layout.activity_onboarding) {

    @Inject
    lateinit var eagerTrigger: EagerTrigger

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: OnBoardingViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        OnboardingComponent.create((application as AppWithFacade).getFacade()).inject(this)
        initObserver()
    }

    override fun onBackPressed() {
        if (viewpager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewpager.currentItem = viewpager.currentItem - 1
        }
    }

    private fun initObserver() {
        viewModel.showOnBoarding.observe(this, Observer { showOnBoarding ->
            if (showOnBoarding) {
                setContentView(R.layout.activity_onboarding)
                setupTabs()
                setSkipBtn()
            }
        })
    }

    private fun setSkipBtn() {
        btnSkip.setOnClickListener {
            finish()
//            MainActivity.display(this)
        }
    }

    private fun setupTabs() {
        viewpager.adapter = OnBoardingPagerAdapter(supportFragmentManager)
        indicator.setViewPager(viewpager)
    }

    private inner class OnBoardingPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        val images = listOf(
            R.drawable.ic_onboarding,
            R.drawable.ic_onboarding_2
        )
        val titles = listOf(
            R.string.onboarding_title_page_1,
            R.string.onboarding_title_page_2
        )

        override fun getItem(position: Int) =
            OnBoardingFragment.newInstance(
                images[position],
                titles[position]
            )

        override fun getCount() = images.size
    }

}
