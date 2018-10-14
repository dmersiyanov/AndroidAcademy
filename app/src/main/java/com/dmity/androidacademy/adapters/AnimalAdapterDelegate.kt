package com.dmity.androidacademy.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dmity.androidacademy.R
import com.dmity.androidacademy.models.AnimalItem
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.utils.DateUtils
import com.dmity.androidacademy.utils.loadImg
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.synthetic.main.item_news_constrained.view.*

class AnimalAdapterDelegate(activity: Activity, private val clickListener: (DisplayableItem) -> Unit):  AdapterDelegate<List<DisplayableItem>>() {

    private val inflater: LayoutInflater = activity.layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return AnimalVH(inflater.inflate(R.layout.item_animal_constrained, parent, false))
    }

    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is AnimalItem
    }

    override fun onBindViewHolder(items: List<DisplayableItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>)
            = (holder as AnimalVH).bind(items[position], clickListener)

    internal class AnimalVH(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: DisplayableItem, listener: (DisplayableItem) -> Unit) = with(itemView) {

            item as AnimalItem

            newsTitle.text = item.title
            newsCategory.text = item.newsCategory?.category
            newsText.text = item.previewText
            newsDate.text = DateUtils.formatDateForNews(item.publishDate, itemView.context)
            newsImage.loadImg(item.imageUrl)
            Glide.with(itemView).load(item.imageUrl).into(newsImage)

            setOnClickListener { listener(item) }
        }

    }




}