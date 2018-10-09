package com.dmity.androidacademy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.models.NewsItem
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : BaseActivity() {

    private lateinit var item: NewsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        initUi()

    }

    private fun getIntentData() {
        intent?.extras?.getParcelable<NewsItem>(ITEM_DATA)?.let {
            item = it
        }
    }

    override fun initUi() {
        getIntentData()
        setupToolbar()
        setupScreen()
    }

    private fun setupScreen() {
        item.let {
            Glide.with(this).load(item.imageUrl).into(detail_image)

            detail_title.text = item.previewText
            detail_date.text = item.publishDate.toString()
            detail_text.text = item.fullText

        }
    }


    private fun setupToolbar() {
        supportActionBar?.title = item.category.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        const val ITEM_DATA = "item_data"

        fun display(activity: AppCompatActivity, item: NewsItem) {
            val intent = Intent(activity, NewsDetailsActivity::class.java).apply {
                putExtra(ITEM_DATA, item)
            }
            activity.startActivity(intent)
        }
    }
}
