package com.dmity.androidacademy.adapters
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmity.androidacademy.R
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.models.NewsItem
import com.dmity.androidacademy.utils.DateUtils
import com.dmity.androidacademy.utils.loadImg
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.synthetic.main.item_news_constrained.view.*


class GenericNewsAdapterDelegate(activity: Activity, private val clickListener: (DisplayableItem) -> Unit): AdapterDelegate<List<DisplayableItem>>() {

    private val inflater: LayoutInflater = activity.layoutInflater

    public override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is NewsItem
    }

    public override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return GenericNewsVH(inflater.inflate(R.layout.item_news_constrained, parent, false))
    }

    override fun onBindViewHolder(items: List<DisplayableItem>, position: Int,
                                  holder: RecyclerView.ViewHolder, payloads: MutableList<Any>)
            = (holder as GenericNewsVH).bind(items[position], clickListener)

    internal class GenericNewsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: DisplayableItem, listener: (DisplayableItem) -> Unit) = with(itemView) {

            item as NewsItem
            newsTitle.text = item.title
            newsCategory.text = item.newsCategory?.category
            newsText.text = item.previewText
            newsDate.text = DateUtils.formatDateForNews(item.publishDate, itemView.context)
            newsImage.loadImg(item.imageUrl)

            setOnClickListener { listener(item) }
        }
    }
}