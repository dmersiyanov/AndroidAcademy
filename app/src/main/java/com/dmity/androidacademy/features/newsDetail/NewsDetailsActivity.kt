package com.dmity.androidacademy.features.newsDetail

import android.content.Context
import android.content.Intent
import com.bumptech.glide.Glide
import com.dmity.androidacademy.R
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.base.Layout
import com.dmity.androidacademy.features.newsList.model.GenericNewsItem
import com.dmity.androidacademy.utils.DateTimeUtils
import com.dmity.androidacademy.utils.visible
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.activity_news_web_view.*
import kotlinx.android.synthetic.main.view_error_stub.*

@Layout(R.layout.activity_news_web_view)
class NewsDetailsActivity : BaseActivity() {

    private lateinit var item: GenericNewsItem
    private var url: String? = ""

    override fun initUi() {
        setupToolbar()
        getUrlFromIntent()
        setupWebView()
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

    private fun setupWebView() {
        if(url.isNullOrBlank()) {
            showError(show = true)
        } else {
            webView.loadUrl(url)
        }
    }

    private fun getUrlFromIntent() {
        intent?.extras?.getString(URL)?.let {
            url = it
        }
    }

    private fun getIntentData() {
        intent?.extras?.getSerializable(ITEM_DATA)?.let {
            item = it as GenericNewsItem
        }
    }

    private fun setupScreen() {
        with(item) {
            Glide.with(this@NewsDetailsActivity).load(imageUrl).into(detailImage)
            detailTitle.text = previewText
            detailDate.text = DateTimeUtils.formatDateForNews(publishDate, this@NewsDetailsActivity)
            detailText.text = fullText
        }
    }

    private fun setupToolbar() {
//        if (item is NewsItem) {
//            supportActionBar?.title = (item as NewsItem).newsCategory.category
//        } else (item as AnimalItem).newsCategory.category

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    companion object {

        private const val ITEM_DATA = "item_data"
        private const val URL = "url"

        fun display(context: Context, item: GenericNewsItem) {
            val intent = Intent(context, NewsDetailsActivity::class.java).apply {
                putExtra(ITEM_DATA, item)
            }
            context.startActivity(intent)
        }

        fun displayWebView(context: Context, url: String?) {
            val intent = Intent(context, NewsDetailsActivity::class.java).apply {
                putExtra(URL, url)
            }
            context.startActivity(intent)
        }
    }
}
