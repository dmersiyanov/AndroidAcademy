package com.dmity.androidacademy.adapters

import com.dmity.androidacademy.models.DisplayableItem
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class NewsAdapter(news: List<DisplayableItem>, clickListener: (DisplayableItem) -> Unit) : ListDelegationAdapter<List<DisplayableItem>>() {

    init {
        delegatesManager.addDelegate(GenericNewsAdapterDelegate(clickListener))
        delegatesManager.addDelegate(AnimalAdapterDelegate(clickListener))
        setItems(news)
    }

}