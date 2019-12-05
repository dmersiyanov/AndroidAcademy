package com.dmity.androidacademy.features.newsList

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dmity.androidacademy.R
import com.dmity.androidacademy.core.SubscriptionsHolder
import com.dmity.androidacademy.database.AppDatabase
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import com.dmity.androidacademy.features.newsList.model.dto.NewsResponseDTO
import com.dmity.androidacademy.features.newsList.model.mapper.NewsItemMapper
import com.dmity.androidacademy.network.RestAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel(application: Application) : AndroidViewModel(application),
    SubscriptionsHolder {

    override val disposables: CompositeDisposable = CompositeDisposable()
    private val mapper: NewsItemMapper = NewsItemMapper()
    private val newsRepo: NewsRepo
    private var currentPosition = -1
    private var context: Context = getApplication()

    var news: MutableLiveData<List<NewsEntity>> = MutableLiveData()
    val showProgress = MutableLiveData<Boolean>()
    val showError = MutableLiveData<Boolean>()
    var showSnackBar = MutableLiveData<Boolean>()

    init {
        newsRepo = NewsRepo(AppDatabase.getAppDataBase(context).newsDao())
        subscribeToData()
    }

    override fun onCleared() {
        resetCompositeDisposable()
    }

    fun getNews(position: Int = currentPosition, retry: Boolean) {
        if (position != currentPosition || retry) {
            loadNews(position)
        }
    }

    private fun subscribeToData() {
        newsRepo.getData()
                .subscribe({
                    if (it.isNotEmpty()) {
                        news.postValue(it)
                    } else {
                        getNews(DEFAULT_CATEGORY, false)
                    }

                }, {
                    Log.e(TAG, it.message)
                })
                .bind()
    }

    private fun loadNews(position: Int = currentPosition) {
        currentPosition = position

        RestAPI.getNews(getCategoryForApi(currentPosition))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    showProgress.postValue(true)
                    showError.postValue(false)
                }
                .subscribe({
                    saveNews(it)
                }, {
                    handleError(it)
                })
                .bind()
    }

    private fun saveNews(response: NewsResponseDTO) {
        val newsForDb = mapper.toDatabase(response, context)
        newsForDb?.let { newsForDbNotNull ->
            newsRepo.saveData(newsForDbNotNull)
                    .subscribe()
                    .bind()
        }
        showProgress.postValue(false)
    }

    private fun handleError(error: Throwable) {
        Log.e(TAG, error.message)
        showProgress.postValue(false)
        showError.postValue(true)
        showSnackBar.postValue(true)
    }

    private fun getCategoryForApi(position: Int): String {
        return context.resources.getStringArray(R.array.news_categories)[position].toLowerCase().trim()
    }

    companion object {
        private const val DEFAULT_CATEGORY = 0
        private const val TAG = "NewsViewModel"
    }

}