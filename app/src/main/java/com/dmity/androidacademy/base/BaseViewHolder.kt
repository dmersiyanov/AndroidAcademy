package com.dmity.androidacademy.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.models.GenericNewsItem
import com.dmity.androidacademy.utils.DateTimeUtils
import com.dmity.androidacademy.utils.loadImg
import kotlinx.android.synthetic.main.item_news_constrained.view.*

class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    // TODO Я правильно понимаю, что у нас будут появляться еще ViewHolder'ы если у нас будут использованы другие модели данных?

    fun bind(item: GenericNewsItem, listener: (DisplayableItem) -> Unit) = with(itemView) {
        newsTitle.text = item.title
        newsCategory.text = item.newsCategory.category
        newsText.text = item.previewText
        newsDate.text = DateTimeUtils.formatDateForNews(item.publishDate, itemView.context)
        newsImage.loadImg(item.imageUrl)
        setOnClickListener { listener(item) }
    }
}