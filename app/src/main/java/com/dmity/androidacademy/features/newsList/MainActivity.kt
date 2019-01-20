package com.dmity.androidacademy.features.newsList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.dmity.androidacademy.R
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.base.Layout
import com.dmity.androidacademy.features.about.AboutActivity
import com.dmity.androidacademy.features.newsDetail.NewsDetailsFragment
import com.dmity.androidacademy.utils.DisplayMetricsUtils.isPhone
import com.dmity.androidacademy.utils.DisplayMetricsUtils.isTablet
import kotlinx.android.synthetic.main.activity_main.*

@Layout(R.layout.activity_main)
class MainActivity : BaseActivity(), NewListFragment.OnNewsClickListener {

    private var isTwoPanel = false

    override fun initUi(savedInstanceState: Bundle?) {

        isTwoPanel = contentContainer != null

        if (isPhone(this)) {
            if (savedInstanceState == null) {
                replaceFragment(R.id.container, NewListFragment.newInstance(), false)
            }
        } else if (isTablet(this)) {
            val container = if (isTwoPanel) R.id.listContainer else R.id.container
            replaceFragment(container, NewListFragment.newInstance(), false)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNewsItemClick(itemId: Int) {
        val container = if(isTwoPanel) R.id.contentContainer else R.id.container
        replaceFragment(container, NewsDetailsFragment.newInstance(itemId), true)
    }

    companion object {
        fun display(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}
