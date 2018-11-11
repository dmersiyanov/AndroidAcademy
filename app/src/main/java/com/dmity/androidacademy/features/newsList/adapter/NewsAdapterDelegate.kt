package com.dmity.androidacademy.features.newsList.adapter

import android.view.ViewGroup
import com.dmity.androidacademy.R
import com.dmity.androidacademy.base.BaseAdapterDelegate
import com.dmity.androidacademy.base.BaseViewHolder
import com.dmity.androidacademy.features.newsList.model.DisplayableItem
import com.dmity.androidacademy.features.newsList.model.dto.NewsItemDTO
import com.dmity.androidacademy.utils.DateTimeUtils
import com.dmity.androidacademy.utils.loadImg
import kotlinx.android.synthetic.main.item_news_constrained.view.*

class NewsAdapterDelegate(private val clickListener: (DisplayableItem) -> Unit) : BaseAdapterDelegate<NewsItemDTO, DisplayableItem, BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder = fromLayoutId(R.layout.item_news_constrained, parent)

    override fun onBindViewHolder(item: NewsItemDTO, holder: BaseViewHolder, payloads: MutableList<Any>) {
        with(holder.itemView) {
            newsTitle.text = item.title
            newsCategory.text = item.subsection
            newsText.text = item.previewText

            newsDate.text = DateTimeUtils.formatDateForNews(item.publishDate!!, context)
            newsImage.loadImg(if (item.multimedia?.isNotEmpty()!!) item.multimedia[0]?.url else "")
            setOnClickListener { clickListener(item) }
        }
    }

    override fun isForViewType(item: DisplayableItem, items: MutableList<DisplayableItem>, position: Int): Boolean = item is NewsItemDTO

}