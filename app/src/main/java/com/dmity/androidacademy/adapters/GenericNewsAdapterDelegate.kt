package com.dmity.androidacademy.adapters

import android.view.ViewGroup
import com.dmity.androidacademy.R
import com.dmity.androidacademy.base.BaseAdapterDelegate
import com.dmity.androidacademy.base.BaseViewHolder
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.models.GenericNewsItem
import com.dmity.androidacademy.models.NewsItem

class GenericNewsAdapterDelegate(private val clickListener: (DisplayableItem) -> Unit) : BaseAdapterDelegate<GenericNewsItem, DisplayableItem, BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder = BaseViewHolder(fromLayoutId(R.layout.item_news_constrained, parent))

    override fun onBindViewHolder(item: GenericNewsItem, holder: BaseViewHolder, payloads: MutableList<Any>) = holder.bind(item, clickListener)

    override fun isForViewType(item: DisplayableItem, items: MutableList<DisplayableItem>, position: Int): Boolean = item is NewsItem

}