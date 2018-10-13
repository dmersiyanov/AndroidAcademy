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
import com.dmity.androidacademy.utils.visible
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class NewsListActivity : BaseActivity() {

    private lateinit var adapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        initUx()
    }

    override fun initUi() {
        initRecycler()
        getNews()
    }

    override fun initUx() {
        btnRetry.setOnClickListener { getNews() }
    }


    private fun getNews() {
        Single.just(DataUtils.generateNews())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .delay(2, TimeUnit.SECONDS)
                .map {
                    throw Exception("Test")
                }
                .doOnSubscribe {
                    showProgress(true)
                    showRetry(false)
                }
                .subscribe({
                    adapter.setItems(it)
                    showProgress(false)
                }, {
                    it.printStackTrace()
                    showRetry(true)
                    showProgress(false)
                    Snackbar.make(rvNews, getString(R.string.error_loading), Snackbar.LENGTH_LONG).show()
                })
                .bind()


    }

    private fun initRecycler() {
        val layoutManager = GridLayoutManager(this, if (isPortrait()) 1 else 2)
        adapter = NewsListAdapter { onNewsItemClick(it) }
        rvNews.layoutManager = layoutManager
        rvNews.adapter = adapter
//        news_rv.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
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

    private fun showProgress(show: Boolean) {
        progress.visible(show)
        rvNews.visible(!show)
    }

    private fun showRetry(show: Boolean) {
        btnRetry.visible(show)
        rvNews.visible(!show)
    }
}
