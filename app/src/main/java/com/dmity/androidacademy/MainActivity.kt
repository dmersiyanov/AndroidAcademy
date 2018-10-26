package com.dmity.androidacademy

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmity.androidacademy.adapters.NewsAdapter
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.models.GenericNewsItem
import com.dmity.androidacademy.network.RestAPI
import com.dmity.androidacademy.utils.DataUtils
import com.dmity.androidacademy.utils.isPortrait
import com.dmity.androidacademy.utils.visible
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_error_stub.*


class MainActivity : BaseActivity() {

    private lateinit var adapter: NewsAdapter
//    private val restAPI = RestAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
    }

    override fun initUi() {
        initRecycler()
        setupSpinner()
        getNews()

    }

    override fun initUx() {
        btnRetry.setOnClickListener { getNews() }
    }

    override fun showProgress(show: Boolean) {
        progress.visible(show)
        rvNews.visible(!show)
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.news_categories,
            R.layout.items_categories_spinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    override fun showError(show: Boolean) = errorStub.visible(show)

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

    private fun getNews() {
        RestAPI.getNews("home")
            .subscribeOn(Schedulers.io())
//                .delay(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                showProgress(true)
                showError(false)
            }
            .subscribe({
                adapter.items = it.results as List<DisplayableItem>

//                    adapter.items = it
                showProgress(false)
            }, {
                it.printStackTrace()
                showError(true)
                showProgress(false)
                Snackbar.make(rvNews, getString(R.string.error_loading), Snackbar.LENGTH_LONG)
                    .show()
            })
            .bind()
    }

    private fun initRecycler() {
        rvNews.layoutManager = if (isPortrait()) LinearLayoutManager(this)
        else GridLayoutManager(this, resources.getInteger(R.integer.landscape_news_columns_count))
        adapter = NewsAdapter(DataUtils.generateNews()) { onNewsItemClick(it) }
        rvNews.adapter = adapter
    }

    private fun onNewsItemClick(item: DisplayableItem) {
        NewsDetailsActivity.display(this, item as GenericNewsItem)
    }


}
