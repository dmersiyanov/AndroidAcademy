package com.dmity.androidacademy.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    open fun initUx() {}
    open fun initUi() {}

}