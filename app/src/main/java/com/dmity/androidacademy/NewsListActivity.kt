package com.dmity.androidacademy

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.dmity.androidacademy.adapters.NewsAdapter
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.utils.DataUtils
import com.dmity.androidacademy.utils.isPortrait
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

    private fun initRecycler() {
        news_rv.layoutManager = GridLayoutManager(this, if (isPortrait()) 1 else 2)
//        news_rv.adapter = NewsListAdapter(DataUtils.generateNews()) { onNewsItemClick(it) }
        news_rv.adapter = NewsAdapter(this, DataUtils.generateNews()) { onNewsItemClick(it) }
    }

    private fun onNewsItemClick(item: DisplayableItem) {
        NewsDetailsActivity.display(this, item)
    }

}
