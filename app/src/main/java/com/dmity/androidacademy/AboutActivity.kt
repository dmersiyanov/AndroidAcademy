package com.dmity.androidacademy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
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

    private fun composeEmail(message: String, email: Array<String> = arrayOf("dmersiyanov@yandex.ru"), subject: String = "Деловое предложение") {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, email)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        if(intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else
            Toast.makeText(this, getString(R.string.error_no_email_app), Toast.LENGTH_SHORT).show()

    }

    private fun openTelegram() {
        try {
            val telegramIntent = Intent(Intent.ACTION_VIEW)
            telegramIntent.data = Uri.parse("http://telegram.me/dmersiyanov")
            startActivity(telegramIntent)

        } catch (e: Exception) {
            Toast.makeText(this, getString(R.string.error_message_send), Toast.LENGTH_SHORT).show()
        }

    }

}
