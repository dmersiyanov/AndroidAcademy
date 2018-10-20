package com.dmity.androidacademy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.models.AnimalItem
import com.dmity.androidacademy.models.GenericNewsItem
import com.dmity.androidacademy.models.NewsItem
import com.dmity.androidacademy.utils.DateTimeUtils
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : BaseActivity() {

    private lateinit var item: GenericNewsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        initUi()

    }

    override fun initUi() {
        getIntentData()
        setupToolbar()
        setupScreen()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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
        if (item is NewsItem) {
            supportActionBar?.title = (item as NewsItem).newsCategory.category
        } else (item as AnimalItem).newsCategory.category

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    companion object {

        private const val ITEM_DATA = "item_data"

        fun display(context: Context, item: GenericNewsItem) {
            val intent = Intent(context, NewsDetailsActivity::class.java).apply {
                putExtra(ITEM_DATA, item)
            }
            context.startActivity(intent)
        }
    }
}
