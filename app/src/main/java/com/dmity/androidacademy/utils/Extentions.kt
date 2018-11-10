package com.dmity.androidacademy.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dmity.androidacademy.R
import com.google.android.material.snackbar.Snackbar


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


fun Context.isPortrait(): Boolean {
    val orientation: Int = resources.configuration.orientation
    return orientation == Configuration.ORIENTATION_PORTRAIT
}

fun ImageView.loadImg(imageUrl: String) {
    if (imageUrl.isBlank()) {
        Glide.with(context).load(R.drawable.ic_placeholder).into(this)
    } else {
        Glide.with(context).load(imageUrl).into(this)
    }
}

fun View.showSnackbar(snackbarText: String, timeLength: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, snackbarText, timeLength).show()
}
