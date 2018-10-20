package com.dmity.androidacademy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.models.AnimalItem
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.models.NewsItem
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : BaseActivity() {

    private lateinit var item: DisplayableItem

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
        intent?.extras?.getParcelable<NewsItem>(ITEM_DATA)?.let {
            item = it
        }
    }

    private fun setupScreen() {
        with(item) {
            Glide.with(this@NewsDetailsActivity).load(imageUrl).into(detail_image)
            detail_title.text = previewText
            detail_date.text = publishDate.toString()
            detail_text.text = fullText
        }
    }

    private fun setupToolbar() {
        supportActionBar?.title = item.category.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }



    private fun getIntentData() {
        intent?.extras?.getSerializable(ITEM_DATA)?.let {
            item = it as DisplayableItem
        }
    }

    // Тут получилась какая-то чушь) но я не вижу другого способа при использовании разных viewType через делегаты :(
    private fun setupScreen() {

        fun setupData(item: NewsItem) {
            with(item) {
                Glide.with(this@NewsDetailsActivity).load(imageUrl).into(detailImage)
                detailTitle.text = previewText
                detailDate.text = publishDate.toString()
                detailText.text = fullText
            }
        }

        fun setupData(item: AnimalItem) {
            with(item) {
                Glide.with(this@NewsDetailsActivity).load(imageUrl).into(detailImage)
                detailTitle.text = previewText
                detailDate.text = publishDate.toString()
                detailText.text = fullText
            }
        }

        with(item) {
            when(this){
                is AnimalItem -> setupData(this)
                is NewsItem -> setupData(this)
            }
        }
    }

    private fun setupToolbar() {
        if (item is NewsItem) {
            supportActionBar?.title = (item as NewsItem).newsCategory?.category
        } else (item as AnimalItem).newsCategory?.category

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    companion object {

        private const val ITEM_DATA = "item_data"

        fun display(context: Context, item: DisplayableItem) {
            val intent = Intent(context, NewsDetailsActivity::class.java).apply {
                putExtra(ITEM_DATA, item)
            }
            context.startActivity(intent)
        }
    }
}
