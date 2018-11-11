package com.dmity.androidacademy

import com.dmity.androidacademy.base.BaseActivity
import com.dmity.androidacademy.base.Layout
import com.dmity.androidacademy.utils.ThirdPartyIntentUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_activity_about.*

@Layout(R.layout.activity_about)
class AboutActivity : BaseActivity() {

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun initUi() {
        setupToolbar()
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
