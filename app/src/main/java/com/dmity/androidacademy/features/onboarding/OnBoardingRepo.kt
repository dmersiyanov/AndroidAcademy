package com.dmity.androidacademy.features.onboarding

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class OnBoardingRepo(context: Context) {

    private var writer: SharedPreferences.Editor
    private val reader: SharedPreferences

    init {
        reader = context.getSharedPreferences(ON_BOARD, MODE_PRIVATE)
        writer = reader.edit()
    }

    fun incrementCounter() {
        val counter = getCounter()
        writer.putInt(LAUNCH_COUNTER, counter + 1).apply()
    }

    fun getCounter(): Int {
        return reader.getInt(LAUNCH_COUNTER, 0)
    }

    companion object {
        private const val ON_BOARD = "on_board"
        private const val LAUNCH_COUNTER = "launch_counter"
    }


}