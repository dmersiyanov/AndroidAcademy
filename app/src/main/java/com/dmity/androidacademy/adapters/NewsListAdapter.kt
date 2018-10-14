package com.dmity.androidacademy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dmity.androidacademy.R
import com.dmity.androidacademy.models.NewsItem
import com.dmity.androidacademy.utils.DateUtils
import kotlinx.android.synthetic.main.item_news_constrained.view.*

// TODO Удалить
class NewsListAdapter(private val items: List<NewsItem>,
                      private val clickListener: (NewsItem) -> Unit
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news_constrained, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], clickListener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: NewsItem, listener: (NewsItem) -> Unit) = with(itemView) {
            newsTitle.text = item.title
            newsCategory.text = item.newsCategory?.category
            newsText.text = item.previewText
            newsDate.text = DateUtils.formatDateForNews(item.publishDate, itemView.context)
            Glide.with(itemView).load(item.imageUrl).into(newsImage)

            setOnClickListener { listener(item) }
        }

    }

}