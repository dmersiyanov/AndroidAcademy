package com.dmity.androidacademy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmity.androidacademy.adapters.NewsAdapter
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.models.GenericNewsItem
import com.dmity.androidacademy.utils.isPortrait
import com.dmity.androidacademy.utils.visible
import com.dmity.androidacademy.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_error_stub.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {

    private lateinit var adapter: NewsAdapter
    private val viewModel: NewsViewModel by lazy {
        ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }

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

    override fun showProgress(show: Boolean) {
        progress.visible(show)
        rvNews.visible(!show)
    }

    override fun showError(errorMessage: String, show: Boolean) {
        if(errorMessage.isNotBlank()) {
            tvErrorMessage.text = errorMessage
        }
        errorStub.visible(show)
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

    private fun getNews() {
        Flowable.fromPublisher(LiveDataReactiveStreams.toPublisher(this, viewModel.getNews()))
                .firstOrError()
                .subscribeOn(Schedulers.io())
                .delay(DELAY, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    showProgress(true)
                    showError("", false)
                }
                .subscribe({
                    adapter.items = it
                    showProgress(false)
                }, {
                    Log.e(TAG, it.message)
                    it.printStackTrace()
                    showError("", true)
                    showProgress(false)
                    Snackbar.make(rvNews, getString(R.string.error_loading), Snackbar.LENGTH_LONG).show()
                })
                .bind()
    }

    private fun initRecycler() {
        rvNews.layoutManager = getLayoutManager()
        adapter = NewsAdapter { onNewsItemClick(it) }
        rvNews.adapter = adapter
    }

    private fun getLayoutManager(): RecyclerView.LayoutManager {
        return when (isPortrait()) {
            true -> LinearLayoutManager(this)
            else -> GridLayoutManager(this, resources.getInteger(R.integer.landscape_news_columns_count))
        }
    }

    private fun onNewsItemClick(item: DisplayableItem) {
        NewsDetailsActivity.display(this, item as GenericNewsItem)
    }

    companion object {
        private const val DELAY = 2L
        private const val TAG = "MainActivity"
    }


}
