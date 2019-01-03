package com.dmity.androidacademy.features.onboarding


import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dmity.androidacademy.R
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.base.Layout
import com.dmity.androidacademy.features.newsList.MainActivity
import kotlinx.android.synthetic.main.activity_onboarding.*

@Layout(R.layout.activity_onboarding)
class OnBoardingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        val viewModel = ViewModelProviders.of(this).get(OnBoardingViewModel::class.java)
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
            MainActivity.display(this)
        }
    }

    private fun setupTabs() {
        viewpager.adapter = OnBoardingPagerAdapter(supportFragmentManager)
        indicator.setViewPager(viewpager)
    }

    private inner class OnBoardingPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

        val images = listOf(R.drawable.ic_onboarding, R.drawable.ic_onboarding_2)
        val titles = listOf(R.string.onboarding_title_page_1, R.string.onboarding_title_page_2)

        override fun getItem(position: Int) = OnBoardingFragment.newInstance(images[position], titles[position])

        override fun getCount() = images.size
    }

}
