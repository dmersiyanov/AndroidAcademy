package com.dmity.androidacademy.core.extensions

import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import com.bumptech.glide.Glide
import com.dmity.androidacademy.core.R
import com.google.android.material.snackbar.Snackbar

fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun Spinner?.addOnClickListener(clickListener: (position: Int) -> Unit) {
    this?.onItemSelectedListener = object :
        AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            clickListener.invoke(position)
        }
    }
}

fun View.showSnack(text: String, timeLength: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, text, timeLength).show()
}

fun ImageView.loadImg(imageUrl: String?) {
    if (imageUrl.isNullOrBlank()) {
        Glide.with(context).load(R.drawable.ic_placeholder).into(this)
    } else {
        Glide.with(context).load(imageUrl).into(this)
    }
}