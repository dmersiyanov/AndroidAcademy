package com.dmity.androidacademy.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dmity.androidacademy.utils.visible
import kotlinx.android.synthetic.main.view_progress_stub.*

abstract class BaseFragment: Fragment() {

    protected abstract val layout: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initUx()
    }

    val context: Context
        @JvmName("getContext2")
        get() = getContext()!!

    protected abstract fun initUx()
    protected abstract fun initUi()
    open fun showProgress(show: Boolean) = progress?.visible(show)



}