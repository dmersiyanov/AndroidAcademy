package com.dmity.androidacademy.utils

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View


fun Context.extractAttrsWithRecycle(set: AttributeSet, id: IntArray, func: TypedArray.() -> Unit) {
    var attributes: TypedArray? = null
    try {
        attributes = obtainStyledAttributes(set, id)
        func(attributes)
    } finally {
        attributes?.recycle()
    }
}

fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}


