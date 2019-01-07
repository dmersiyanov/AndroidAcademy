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
import com.dmity.androidacademy.utils.DisplayMetricsUtils.isLandscape
import com.dmity.androidacademy.utils.DisplayMetricsUtils.isTablet
import kotlinx.android.synthetic.main.activity_main_frame_layout.*

@Layout(R.layout.activity_main_frame_layout)
class MainActivity : BaseActivity(), NewListFragment.OnNewsClickListener {

    private var isTwoPanel = false

    override fun initUi(savedInstanceState: Bundle?) {

        isTwoPanel = contentContainer != null

        if (savedInstanceState == null) {
            supportFragmentManager
                        .beginTransaction()
                        .add(R.id.container, NewListFragment.newInstance())
                        .commit()
        } else {
            if (isTablet(this) && isLandscape(this)) {
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.listContainer, NewListFragment.newInstance())
                        .commit()
            }
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
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(container, NewsDetailsFragment.newInstance(itemId))
                .commit()
    }

    companion object {
        fun display(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}
