package com.dmity.androidacademy.adapters

import android.app.Activity
import com.dmity.androidacademy.models.DisplayableItem
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class NewsAdapter(activity: Activity, news: List<DisplayableItem>, clickListener: (DisplayableItem) -> Unit) : ListDelegationAdapter<List<DisplayableItem>>() {

    init {
        delegatesManager.addDelegate(GenericNewsAdapterDelegate(activity, clickListener))
        delegatesManager.addDelegate(AnimalAdapterDelegate(activity, clickListener))
        setItems(news)
    }

}
