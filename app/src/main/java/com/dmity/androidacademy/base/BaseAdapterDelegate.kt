package com.dmity.androidacademy.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

abstract class BaseAdapterDelegate<I : T, T, VH : RecyclerView.ViewHolder>: AbsListItemAdapterDelegate<I, T, VH>() {

    private lateinit var inflater: LayoutInflater

    protected fun fromLayoutId(layoutId: Int, parent: ViewGroup): View {
        inflater = LayoutInflater.from(parent.context)
        return inflater.inflate(layoutId, parent, false)
    }

}