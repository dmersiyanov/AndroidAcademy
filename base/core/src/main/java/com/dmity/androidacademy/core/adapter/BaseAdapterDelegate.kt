package com.dmity.androidacademy.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

abstract class BaseAdapterDelegate<I : T, T, VH : RecyclerView.ViewHolder>: AbsListItemAdapterDelegate<I, T, VH>() {

    private lateinit var inflater: LayoutInflater

    protected fun fromLayoutId(layoutId: Int, parent: ViewGroup): BaseViewHolder {
        inflater = LayoutInflater.from(parent.context)
        return BaseViewHolder(
            inflater.inflate(
                layoutId,
                parent,
                false
            )
        )
    }

}