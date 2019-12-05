package com.dmity.androidacademy.core.extensions

import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.view_error_stub.*
import kotlinx.android.synthetic.main.view_progress_stub.*

fun Fragment.showProgress(show: Boolean) = progress?.visible(show)

fun Fragment.showError(errorMessage: String, show: Boolean) {
    if (errorMessage.isNotBlank()) {
        tvErrorMessage.text = errorMessage
    }
    errorStub.visible(show)
}

fun Fragment.setRetryListener(clickListener: View.OnClickListener) {
    btnRetry.setOnClickListener(clickListener)
}

fun Fragment.setRetryListener(listener: () -> Unit) {
    btnRetry.setOnClickListener {
        listener.invoke()
    }
}
