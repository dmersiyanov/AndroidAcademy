package com.dmity.androidacademy.Utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.dmity.androidacademy.AboutActivity

object ThirdPartyIntentUtils {

     fun getEmailIntent(message: String, email: Array<String> = arrayOf(AboutActivity.EMAIL), subject: String = AboutActivity.SUBJECT, context: Activity): Intent {
        return Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, email)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }
    }

    fun getTelegramIntent() {

    }

}