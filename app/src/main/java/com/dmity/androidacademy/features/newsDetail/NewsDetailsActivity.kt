package com.dmity.androidacademy.features.newsDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.dmity.androidacademy.R
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.base.Layout
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import com.dmity.androidacademy.utils.visible
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.view_error_stub.*

@Layout(R.layout.activity_news_details)
class NewsDetailsActivity : BaseActivity() {

    private val viewModel: NewsDetailsViewModel by lazy {
        ViewModelProviders.of(this).get(NewsDetailsViewModel::class.java)
    }

    override fun initUi(savedInstanceState: Bundle?) {
        initObservers()
        getIntentData()
    }

    private fun initObservers() {
        viewModel.showProgress.observe(this, Observer {
            showProgress(it)
        })
        viewModel.showError.observe(this, Observer {
            showError(getString(R.string.error_view_text), it)
        })

        viewModel.newsItem.observe(this, Observer { item ->
            setupScreen(item)
        })

    }

    override fun initUx() {
        btnRetry.setOnClickListener { onBackPressed() }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showError(errorMessage: String, show: Boolean) {
        errorStub.visible(show)
    }

    private fun getIntentData() {
        viewModel.loadNewsItem(intent?.extras?.getInt(ARGS_ITEM_ID))
    }

    private fun setupScreen(newsEntity: NewsEntity) {
        with(newsEntity) {
            Glide.with(this@NewsDetailsActivity).load(imageUrl).into(detailImage)
            detailTitle.text = title
            detailDate.text = publishDate
            detailText.text = fullText
            setupToolbar(newsCategory)
        }
    }

    private fun setupToolbar(title: String) {
        if (title.isNotBlank()) {
            supportActionBar?.title = title
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    companion object {

        private const val ARGS_ITEM_ID = "item_id"

        fun display(context: Context, itemId: Int?) {
            val intent = Intent(context, NewsDetailsActivity::class.java).apply {
                putExtra(ARGS_ITEM_ID, itemId)
            }
            context.startActivity(intent)
        }
    }
}
