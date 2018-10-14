package com.dmity.androidacademy.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.TypedArray
import android.util.AttributeSet


fun Context.extractAttrsWithRecycle(set: AttributeSet, id: IntArray, func: TypedArray.() -> Unit) {
    var attributes: TypedArray? = null
    try {
        attributes = obtainStyledAttributes(set, id)
        func(attributes)
    } finally {
        attributes?.recycle()
    }
}

fun Activity.isPortrait(): Boolean {
    val orientation: Int = resources.configuration.orientation
    return orientation == Configuration.ORIENTATION_PORTRAIT
}

