package com.dmity.androidacademy.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

object ThirdPartyIntentUtils {

    private const val EMAIL = "dmersiyanov@yandex.ru"
    private const val SUBJECT = "Деловое предложение"
    private const val TELEGRAM_LINK = "http://telegram.me/dmersiyanov"

    fun getEmailIntent(message: String, context: Context): Intent? {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = getEmailUri(message)
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

    private fun getEmailUri(message: String): Uri {
        return  Uri.parse( "mailto:$EMAIL?subject=${Uri.encode(SUBJECT)}&body=${Uri.encode(message)}")
    }
}