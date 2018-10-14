package com.dmity.androidacademy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dmity.androidacademy.R
import com.dmity.androidacademy.models.NewsItem
import kotlinx.android.synthetic.main.news_item.view.*


class NewsListAdapter(private val items: List<NewsItem>,
                      private val clickListener: (NewsItem) -> Unit
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], clickListener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: NewsItem, listener: (NewsItem) -> Unit) = with(itemView) {
            news_title.text = item.title
            news_category.text = item.category.name
            news_text.text = item.previewText
            news_date.text = item.publishDate.toString()
            Glide.with(itemView).load(item.imageUrl).into(news_image)

            setOnClickListener { listener(item) }
        }

    }

}