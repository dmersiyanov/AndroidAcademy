package com.dmity.androidacademy.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dmity.androidacademy.utils.visible
import kotlinx.android.synthetic.main.view_progress_stub.*

open class BaseFragment: Fragment() {

    val context: Context
        @JvmName("getContext2")
        get() = getContext()!!

    open fun initUx() {}
    open fun initUi() {}
    open fun initUi(savedInstanceState: Bundle?) {}
    open fun showProgress(show: Boolean) = progress?.visible(show)
}