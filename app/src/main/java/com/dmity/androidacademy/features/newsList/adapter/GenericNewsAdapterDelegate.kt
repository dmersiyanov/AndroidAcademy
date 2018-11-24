package com.dmity.androidacademy.features.newsList.adapter

import android.view.ViewGroup
import com.dmity.androidacademy.R
import com.dmity.androidacademy.base.BaseAdapterDelegate
import com.dmity.androidacademy.base.BaseViewHolder
import com.dmity.androidacademy.features.newsList.model.DisplayableItem
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import com.dmity.androidacademy.utils.loadImg
import kotlinx.android.synthetic.main.item_news_constrained.view.*

class GenericNewsAdapterDelegate(private val clickListener: (DisplayableItem) -> Unit) : BaseAdapterDelegate<NewsEntity, DisplayableItem, BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder = fromLayoutId(R.layout.item_news_constrained, parent)

    override fun onBindViewHolder(item: NewsEntity, holder: BaseViewHolder, payloads: MutableList<Any>) {
        with(holder.itemView) {
            newsTitle.text = item.title
            newsCategory.text = item.newsCategory
            newsText.text = item.previewText
//            newsDate.text = DateTimeUtils.formatDateForNews(item.publishDate, context)
            newsImage.loadImg(item.imageUrl)
            setOnClickListener { clickListener(item) }
        }
    }

    override fun isForViewType(item: DisplayableItem, items: MutableList<DisplayableItem>, position: Int): Boolean = item is NewsEntity

}