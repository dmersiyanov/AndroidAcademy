package com.dmity.androidacademy.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.dmity.androidacademy.R
import com.dmity.androidacademy.core.extensions.extractAttrsWithRecycle
import kotlinx.android.synthetic.main.layout_image_text_view.view.*


class ImageTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.layout_image_text_view, this)

        attrs?.let { attr ->
            context.extractAttrsWithRecycle(attr, R.styleable.ImageTextView) {
                text.text = getString(R.styleable.ImageTextView_text)
                image.setImageDrawable(getDrawable(R.styleable.ImageTextView_image))
            }
        }
    }
}

