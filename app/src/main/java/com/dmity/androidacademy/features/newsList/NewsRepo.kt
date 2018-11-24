package com.dmity.androidacademy.features.newsList

import android.content.Context
import com.dmity.androidacademy.database.AppDatabase
import com.dmity.androidacademy.database.NewsDao
import com.dmity.androidacademy.database.NewsDaoAsync
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class NewsRepo(private val context: Context) {

    private val newsDao: NewsDao?
        get() = AppDatabase.getAppDataBase(context)?.newsDao()

    private val newsDaoAsync: NewsDaoAsync?
        get() = AppDatabase.getAppDataBase(context)?.newsDaoAsync()

    fun saveData(filmList: List<NewsEntity>): Completable = Completable.fromCallable {
        newsDao?.let {
            it.deleteAll()
            it.insertAll(filmList)
        }
    }.subscribeOn(Schedulers.io())

    fun getData() = newsDaoAsync?.getAll()
            ?.observeOn(Schedulers.io())
            ?.subscribeOn(AndroidSchedulers.mainThread())

    fun clear(): Completable = Completable.fromAction {
        newsDao?.deleteAll()
    }
}