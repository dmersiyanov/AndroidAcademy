package com.dmity.androidacademy

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmity.androidacademy.adapters.NewsAdapter
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.models.GenericNewsItem
import com.dmity.androidacademy.utils.isPortrait
import com.dmity.androidacademy.utils.showSnackbar
import com.dmity.androidacademy.utils.visible
import com.dmity.androidacademy.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_error_stub.*


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
        setupSpinner()

        initObservers()
    }

    override fun initUx() {
        btnRetry.setOnClickListener { viewModel.getNews() }
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
    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.news_categories,
            R.layout.items_categories_spinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
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

    private fun initObservers() {
        viewModel.showProgress.observe(this, Observer { showProgress(it) })
        viewModel.showError.observe(this, Observer { showError(getString(R.string.error_loading), it) })
        viewModel.showSnackbar.observe(this, Observer { rvNews.showSnackbar(getString(R.string.error_loading)) })

        viewModel.news.observe(this, Observer { items ->
            adapter.items = items
            rvNews.visible(true)
        })
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


}
