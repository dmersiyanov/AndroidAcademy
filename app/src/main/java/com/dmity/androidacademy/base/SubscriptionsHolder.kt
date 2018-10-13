package com.dmity.androidacademy.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface SubscriptionsHolder {

    /**
     * Holds Disposables
     */
    val mDisposable: CompositeDisposable

    /**
     * Easy activate disposables to composite with this function
     */
    fun Disposable.bind() = mDisposable.add(this)

    /**
     * Clear all subscriptions when needed
     */
    fun resetCompositeDisposable() {
        mDisposable.clear()
    }


}