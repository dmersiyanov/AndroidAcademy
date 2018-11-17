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

    private val url by lazy {
        intent?.extras?.getString(ARGS_URL) ?: ""
    }

    override fun initUi() {
        setupToolbar()
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
        if(url.isBlank()) {
            showError(show = true)
        } else {
            webView.loadUrl(url)
        }
    }

    private fun getIntentData() {
        intent?.extras?.getSerializable(ARGS_ITEM_DATA)?.let {
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

    private fun setupToolbar() = supportActionBar?.setDisplayHomeAsUpEnabled(true)



    companion object {

        private const val ARGS_ITEM_DATA = "item_data"
        private const val ARGS_URL = "url"

        fun display(context: Context, item: GenericNewsItem) {
            val intent = Intent(context, NewsDetailsActivity::class.java).apply {
                putExtra(ARGS_ITEM_DATA, item)
            }
            context.startActivity(intent)
        }

        fun displayWebView(context: Context, url: String?) {
            val intent = Intent(context, NewsDetailsActivity::class.java).apply {
                putExtra(ARGS_URL, url)
            }
            context.startActivity(intent)
        }
    }
}
