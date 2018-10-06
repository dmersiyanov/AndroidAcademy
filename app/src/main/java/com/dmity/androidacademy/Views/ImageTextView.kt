package com.dmity.androidacademy.Views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.dmity.androidacademy.R
import kotlinx.android.synthetic.main.layout_image_text_view.view.*


class ImageTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.layout_image_text_view, this)
        val attrs = context.obtainStyledAttributes(attrs, R.styleable.ImageTextView)

        try {
            text.text = attrs.getString(R.styleable.ImageTextView_text)
            image.setImageDrawable(attrs.getDrawable(R.styleable.ImageTextView_image))

        } finally {
            attrs.recycle()

        }
    }
}

