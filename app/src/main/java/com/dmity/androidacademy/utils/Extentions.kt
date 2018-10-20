package com.dmity.androidacademy.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dmity.androidacademy.R


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

fun ImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Glide.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Glide.with(context).load(imageUrl).into(this)
    }
}

