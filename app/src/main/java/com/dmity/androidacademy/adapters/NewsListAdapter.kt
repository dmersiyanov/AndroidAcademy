package com.dmity.androidacademy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dmity.androidacademy.R
import com.dmity.androidacademy.models.NewsItem
import kotlinx.android.synthetic.main.item_news.view.*

// TODO Удалить
class NewsListAdapter(private val items: List<NewsItem>,
                      private val clickListener: (NewsItem) -> Unit
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], clickListener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         fun bind(item: NewsItem, listener: (NewsItem) -> Unit) = with(itemView) {
             newsTitle.text = item.title
             newsCategory.text = item.newsCategory?.name
             newsText.text = item.previewText
             newsDate.text = item.publishDate.toString()
             Glide.with(itemView).load(item.imageUrl).into(newsImage)

            setOnClickListener { listener(item) }
        }

    }

}