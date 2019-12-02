package com.dmity.androidacademy.features.newsList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import com.dmity.androidacademy.R
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.base.Layout
import com.dmity.androidacademy.features.newsDetail.NewsDetailsFragment
import com.dmity.androidacademy.utils.DisplayMetricsUtils.isPhone
import com.dmity.androidacademy.utils.DisplayMetricsUtils.isTablet
import com.dmity.androidacademy.utils.addOnClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main_tablet.*

@Layout(R.layout.activity_main)
class MainActivity : BaseActivity(), NewListFragment.OnNewsClickListener {

    private var isTwoPanel = false

    private val viewModel: NewsViewModel by lazy {
        ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }

    override fun initUi(savedInstanceState: Bundle?) {

        isTwoPanel = contentContainer != null

        if (isPhone(this)) {
            if (savedInstanceState == null) {
                replaceFragment(R.id.container, NewListFragment.newInstance(), false)
            }
        } else if (isTablet(this)) {
            val container = if (isTwoPanel) R.id.listContainer else R.id.container
            replaceFragment(container, NewListFragment.newInstance(), false)

            setupSpinner()
            setupTabletLand()
        }
    }

    override fun onNewsItemClick(itemId: Int) {
        val container = if(isTwoPanel) R.id.contentContainer else R.id.container
        replaceFragment(container, NewsDetailsFragment.newInstance(itemId), true)
    }

    private fun setupTabletLand() {
        fab?.setOnClickListener{ viewModel.getNews(retry = true) }
        spinner?.addOnClickListener { position ->
            viewModel.getNews(position, false)
        }
    }

    private fun setupSpinner() {
        spinner?.let {
            val adapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.news_categories,
                    R.layout.items_categories_spinner
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            it.adapter = adapter
        }
    }

    companion object {
        fun display(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent)
        }
    }

}
