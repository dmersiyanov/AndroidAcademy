package com.dmity.androidacademy.core.utils

import android.content.Context
import android.widget.Toast
import com.dmity.androidacademy.domain.system.AndroidPlatformProxy
import javax.inject.Inject

class AndroidPlatformProxyImpl @Inject constructor(
    private val context: Context
) : AndroidPlatformProxy {

    override fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}