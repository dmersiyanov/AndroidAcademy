package com.dmity.androidacademy.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

object ThirdPartyIntentUtils {

    private const val EMAIL = "dmersiyanov@yandex.ru"
    private const val SUBJECT = "Деловое предложение"
    const val TELEGRAM_LINK = "http://telegram.me/dmersiyanov"

    fun getEmailIntent(message: String, email: Array<String> = arrayOf(EMAIL),
                       subject: String = SUBJECT, context: Context): Intent? {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, email)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }
        return if (intent.resolveActivity(context.packageManager) != null) intent
        else null
    }

    fun getTelegramIntent(context: Context): Intent? {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(TELEGRAM_LINK)
        }

        return if (intent.resolveActivity(context.packageManager) != null) intent
        else null
    }
}