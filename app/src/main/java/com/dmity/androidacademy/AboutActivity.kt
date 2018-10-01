package com.dmity.androidacademy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.dmity.androidacademy.Utils.ThirdPartyIntentUtils
import kotlinx.android.synthetic.main.activity_about.*


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setupToolbar()
        initUx()

    }

    private fun setupToolbar() {
        supportActionBar?.title = getString(R.string.my_name)
    }

    private fun initUx() {
        btn_send.setOnClickListener { composeEmail(message.text.toString()) }
        btn_telegram.setOnClickListener { openTelegram() }
    }

    private fun composeEmail(message: String, email: Array<String> = arrayOf(EMAIL), subject: String = SUBJECT) {
        val intent = ThirdPartyIntentUtils.getEmailIntent(message, email, subject, this)

        if(intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else
            Snackbar.make(btn_send, getString(R.string.error_no_email_app), Snackbar.LENGTH_LONG).show()


    }

    private fun openTelegram() {
        try {
            val telegramIntent = Intent(Intent.ACTION_VIEW)
            telegramIntent.data = Uri.parse(TELEGRAM_LINK)
            startActivity(telegramIntent)

        } catch (e: Exception) {
            Snackbar.make(btn_telegram, getString(R.string.error_message_send), Snackbar.LENGTH_LONG).show()
        }

    }

    companion object {
        const val EMAIL = "dmersiyanov@yandex.ru"
        const val SUBJECT = "Деловое предложение"
        const val TELEGRAM_LINK = "http://telegram.me/dmersiyanov"
    }

}
