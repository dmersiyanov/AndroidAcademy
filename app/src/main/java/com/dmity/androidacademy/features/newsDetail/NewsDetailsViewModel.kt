package com.dmity.androidacademy.features.newsDetail

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dmity.androidacademy.base.SubscriptionsHolder
import com.dmity.androidacademy.database.AppDatabase
import com.dmity.androidacademy.features.newsList.NewsRepo
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsDetailsViewModel(application: Application) : AndroidViewModel(application), SubscriptionsHolder {

    override val disposables: CompositeDisposable = CompositeDisposable()
    private val newsRepo: NewsRepo
    private var context: Context = getApplication()

    var newsItem: MutableLiveData<NewsEntity> = MutableLiveData()
    val showProgress = MutableLiveData<Boolean>()
    val showError = MutableLiveData<Boolean>()

    init {
        newsRepo = NewsRepo(AppDatabase.getAppDataBase(context).newsDao())
    }

    override fun onCleared() {
        resetCompositeDisposable()
    }

    fun loadNewsItem(id: Int?) {
        if (id == null) {
            handleError(null)
        } else {
            newsRepo.getNewsById(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        showProgress.postValue(true)
                        showError.postValue(false)
                    }
                    .subscribe({
                        newsItem.postValue(it)
                        showProgress.postValue(false)
                    }, {
                        handleError(it)
                    })
                    .bind()
        }
    }

    private fun handleError(error: Throwable?) {
        Log.e(TAG, error?.message ?: "")
        showProgress.postValue(false)
        showError.postValue(true)
    }

    private companion object {
        const val TAG = "NewsDetailsViewModel"
    }
}