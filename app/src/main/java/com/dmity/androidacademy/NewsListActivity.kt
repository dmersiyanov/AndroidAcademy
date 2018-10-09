package com.dmity.androidacademy

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.dmity.androidacademy.adapters.NewsListAdapter
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.models.NewsItem
import com.dmity.androidacademy.utils.DataUtils
import kotlinx.android.synthetic.main.activity_main.*

class NewsListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
    }

    override fun initUi() {
        initRecycler()
    }

    private fun initRecycler() {
        news_rv.layoutManager = GridLayoutManager(this, if (isPortrait()) 1 else 2)
        news_rv.adapter = NewsListAdapter(DataUtils.generateNews()) { onNewsItemClick(it) }
    }

    private fun onNewsItemClick(item: NewsItem) {
        Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        NewsDetailsActivity.display(this, item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Куда и как лучше вынести эту функцию на случай если она понадобится в других классах?
    private fun isPortrait(): Boolean {
        val orientation: Int = resources.configuration.orientation
        return orientation == Configuration.ORIENTATION_PORTRAIT
    }
}
