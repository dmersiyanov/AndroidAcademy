package com.dmity.androidacademy.utils

import android.content.Context
import android.text.format.DateUtils
import com.dmity.androidacademy.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit



object DateTimeUtils {

    private const val FULL = "MMM dd, hh:mm a"
    private const val TIME = "hh:mm a"

    fun formatDateForNews(date: Date?, context: Context) : String {
        return when {
            date == null -> ""
            DateUtils.isToday(date.time) -> {
                val hoursAgo = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - date.time)
                context.getString(R.string.text_hours, hoursAgo, SimpleDateFormat(TIME, Locale.ENGLISH).format(date))
            }
            isYesterday(date) -> context.getString(R.string.text_yesterday, SimpleDateFormat(TIME, Locale.ENGLISH).format(date))
            else -> SimpleDateFormat(FULL, Locale.ENGLISH).format(date)
        }
    }

    fun coolFormatDateForNews(date: Date, context: Context) : CharSequence {
        return DateUtils.getRelativeDateTimeString(
                context,
                date.time,
                DateUtils.HOUR_IN_MILLIS,
                5 * DateUtils.DAY_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_RELATIVE
        )
    }

    private fun isYesterday(d: Date): Boolean = DateUtils.isToday(d.time + DateUtils.DAY_IN_MILLIS)

}