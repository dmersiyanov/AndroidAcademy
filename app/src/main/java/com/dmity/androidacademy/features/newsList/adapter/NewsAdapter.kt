package com.dmity.androidacademy.features.newsList.adapter

import com.dmity.androidacademy.features.newsList.model.DisplayableItem
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class NewsAdapter(clickListener: (DisplayableItem) -> Unit) : ListDelegationAdapter<List<DisplayableItem>>() {

    init {
        delegatesManager.addDelegate(BaseNewsAdapterDelegate(clickListener))
    }

    fun setData(items: List<DisplayableItem>) {
        setItems(items)
        notifyDataSetChanged()
    }

}
