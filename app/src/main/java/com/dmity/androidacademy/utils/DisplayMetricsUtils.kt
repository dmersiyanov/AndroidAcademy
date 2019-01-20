package com.dmity.androidacademy.utils

import android.content.Context
import android.content.res.Configuration

object DisplayMetricsUtils {

    fun toPx(context: Context, dp: Double): Int {
        return (dp * getDpi(context) + 0.5f).toInt()
    }

    fun toDp(context: Context, px: Int): Double {
        return (px / getDpi(context)).toDouble()
    }

    fun getDpi(context: Context): Float {
        return context.resources.displayMetrics.density
    }


    fun getWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    fun getHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    fun isPhone(context: Context): Boolean {
        if (getWidth(context) < getHeight(context))
            return toDp(context, getWidth(context)) <= 480

        return toDp(context, getHeight(context)) <= 480
    }

    fun isTablet(context: Context): Boolean {
        return !isPhone(context)
    }

    fun isPortrait(context: Context): Boolean =
            context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    fun isLandscape(context: Context): Boolean =
            !isPortrait(context)

    fun isTabletLand(context: Context): Boolean {
        return isTablet(context) && isLandscape(context)
    }




}