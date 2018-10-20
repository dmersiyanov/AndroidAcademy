package com.dmity.androidacademy.utils

import android.content.Context
import android.text.format.DateUtils
import com.dmity.androidacademy.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit



object DateUtils {

    private const val FULL = "MMM dd, hh:mm a"
    private const val TIME = "hh:mm a"

    fun formatDateForNews(date: Date, context: Context) : String {
        return when(DateUtils.isToday(date.time)) {
            true -> {
                val hoursAgo = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - date.time)
                context.getString(R.string.text_hours, hoursAgo, SimpleDateFormat(TIME, Locale.ENGLISH).format(date))
            }
            else -> {
                if (isYesterday(date)) {
                    context.getString(R.string.text_yesterday, SimpleDateFormat(TIME, Locale.ENGLISH).format(date))
                } else {
                    SimpleDateFormat(FULL, Locale.ENGLISH).format(date)
                }
            }
        }
    }

    private fun isYesterday(d: Date): Boolean = DateUtils.isToday(d.time + DateUtils.DAY_IN_MILLIS)

}