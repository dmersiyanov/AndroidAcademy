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

@Layout(R.layout.activity_main_frame_layout)
class MainActivity : BaseActivity(), NewListFragment.OnNewsClickListener {

    override fun initUi(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, NewListFragment.newInstance())
                    .commit()
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
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, NewsDetailsFragment.newInstance(itemId))
                .commit()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

//    private lateinit var adapter: NewsAdapter
//    private val viewModel: NewsViewModel by lazy {
//        ViewModelProviders.of(this).get(NewsViewModel::class.java)
//    }
//
//    override fun initUi() {
//        initRecycler()
//        setupSpinner()
//        setupActionBar(R.id.toolbar) {}
//        initObservers()
//    }
//
//    override fun initUx() {
//        val clickListener = View.OnClickListener {
//            viewModel.getNews(retry = true)
//        }
//
//        btnRetry.setOnClickListener(clickListener)
//        fab.setOnClickListener(clickListener)
//        setupSpinnerListener()
//    }
//
//    override fun showError(errorMessage: String, show: Boolean) {
//        if(errorMessage.isNotBlank()) {
//            tvErrorMessage.text = errorMessage
//        }
//        errorStub.visible(show)
//    }
//
//    private fun showSnackBar(text: String, show: Boolean) {
//        if (show && text.isNotBlank()) {
//            rvNews.showSnack(text)
//            resetSnackBar()
//        }
//    }
//
//    private fun resetSnackBar() {
//        viewModel.showSnackBar.postValue(false)
//    }
//
//    private fun setupSpinnerListener() {
//        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                viewModel.getNews(position, false)
//            }
//        }
//    }
//
//    private fun setupSpinner() {
//        val adapter = ArrayAdapter.createFromResource(
//            this,
//                R.array.news_categories,
//                R.layout.items_categories_spinner
//        )
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = adapter
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_list, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_about -> {
//                startActivity(Intent(this, AboutActivity::class.java))
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//
//    private fun initObservers() {
//        viewModel.showProgress.observe(this, Observer { showProgress(it) })
//        viewModel.showError.observe(this, Observer { showError(getString(R.string.error_view_text), it) })
//        viewModel.showSnackBar.observe(this, Observer { showSnackBar(getString(R.string.error_loading), it) })
//
//        viewModel.news.observe(this, Observer { items ->
//            adapter.setData(items)
//            rvNews.visible(true)
//        })
//
//    }
//
//    private fun initRecycler() {
//        rvNews.layoutManager = getLayoutManager()
//        adapter = NewsAdapter { onNewsItemClick(it) }
//        rvNews.adapter = adapter
//    }
//
//    private fun getLayoutManager(): RecyclerView.LayoutManager {
//        return when (isPortrait()) {
//            true -> LinearLayoutManager(this)
//            else -> GridLayoutManager(this, resources.getInteger(R.integer.landscape_news_columns_count))
//        }
//    }
//
//    private fun onNewsItemClick(item: DisplayableItem) {
//        (item as NewsEntity).id?.let { NewsDetailsActivity.display(this, it) }
//    }
//
    companion object {
        fun display(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}
