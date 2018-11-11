package com.dmity.androidacademy.features.newsList

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dmity.androidacademy.R
import com.dmity.androidacademy.base.SubscriptionsHolder
import com.dmity.androidacademy.features.newsList.model.DisplayableItem
import com.dmity.androidacademy.network.RestAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class NewsViewModel(application: Application): AndroidViewModel(application), SubscriptionsHolder {

    override val disposables: CompositeDisposable = CompositeDisposable()
    private var currentPosition = -1
    private var context: Context = getApplication()

    var news: MutableLiveData<List<DisplayableItem>> = MutableLiveData()
    val showProgress = MutableLiveData<Boolean>()
    val showError = MutableLiveData<Boolean>()
    var showSnackBar = MutableLiveData<Boolean>()

    init {
        getNews(DEFAULT_CATEGORY, false)
    }

    override fun onCleared() {
        resetCompositeDisposable()
    }

    fun getNews(position: Int = currentPosition, retry: Boolean) {
        if (position != currentPosition || retry) {
            loadNews(position)
        }
    }

    private fun loadNews(position: Int = currentPosition) {
        currentPosition = position

        RestAPI.getNews(getCategoryForApi(currentPosition))
                .subscribeOn(Schedulers.io())
                .delay(DELAY_IN_SECONDS, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    showProgress.value = true
                    showError.value = false
                }
                .subscribe({
                    news.value = it.results as List<DisplayableItem>
                    showProgress.value = false
                }, {
                    Log.e(TAG, it.message)
                    showProgress.value = false
                    showError.value = true
                    showSnackBar.value = true
                })
                .bind()
    }

    private fun getCategoryForApi(position: Int): String {
        return context.resources.getStringArray(R.array.news_categories)[position].toLowerCase().trim()
    }

    companion object {
        private const val DELAY_IN_SECONDS = 2L
        private const val DEFAULT_CATEGORY = 0
        private const val TAG = "NewsViewModel"
    }

}