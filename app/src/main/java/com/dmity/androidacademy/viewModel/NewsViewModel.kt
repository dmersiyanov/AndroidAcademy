package com.dmity.androidacademy.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmity.androidacademy.base.SubscriptionsHolder
import com.dmity.androidacademy.models.DisplayableItem
import com.dmity.androidacademy.utils.DataUtils
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class NewsViewModel: ViewModel(), SubscriptionsHolder {

    override val disposables: CompositeDisposable = CompositeDisposable()

    var news: MutableLiveData<List<DisplayableItem>> = MutableLiveData()
    val showProgress = MutableLiveData<Boolean>()
    val showError = MutableLiveData<Boolean>()
    val showSnackbar = MutableLiveData<Boolean>()

    init {
        getNews()
    }

    override fun onCleared() {
        resetCompositeDisposable()
    }

    fun getNews() {
        Single.fromCallable { DataUtils.generateNews()  }
                .subscribeOn(Schedulers.io())
                .delay(DELAY, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    showProgress.value = true
                    showError.value = false
                }
                .subscribe({
                    news.value = it
                    showProgress.value = false
                }, {
                    Log.e(TAG, it.message)
                    showProgress.value = false
                    showError.value = true
                    showSnackbar.value = true
                })
                .bind()
    }

    companion object {
        private const val DELAY = 2L
        private const val TAG = "NewsViewModel"
    }

}