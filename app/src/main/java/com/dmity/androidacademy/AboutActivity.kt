package com.dmity.androidacademy

import android.os.Bundle
import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.utils.ThirdPartyIntentUtils
import com.google.android.material.snackbar.Snackbar
import io.reactivex.internal.functions.Functions.emptyConsumer
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.android.synthetic.main.content_activity_about.*


class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        RxJavaPlugins.setErrorHandler(emptyConsumer());

        setupToolbar()
        initUx()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun initUx() {
        btn_send.setOnClickListener { composeEmail(message.text.toString()) }
        btn_telegram.setOnClickListener { openTelegram() }
    }


    private fun setupToolbar() {
        supportActionBar?.title = getString(R.string.my_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun composeEmail(message: String) {
        val intent = ThirdPartyIntentUtils.getEmailIntent(message, this)
        if (intent != null) {
            startActivity(intent)
        } else
            Snackbar.make(btn_send, getString(R.string.error_no_email_app), Snackbar.LENGTH_LONG).show()
    }

    private fun openTelegram() {
        val telegramIntent = ThirdPartyIntentUtils.getTelegramIntent(this)
        if (telegramIntent != null) {
            startActivity(telegramIntent)
        } else
            Snackbar.make(btn_telegram, getString(R.string.error_message_send), Snackbar.LENGTH_LONG).show()
    }
}
