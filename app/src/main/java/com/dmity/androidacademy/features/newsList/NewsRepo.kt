package com.dmity.androidacademy.features.newsList

import com.dmity.androidacademy.database.NewsDao
import com.dmity.androidacademy.database.NewsDaoAsync
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class NewsRepo(private val newsDao: NewsDao, private val newsDaoAsync: NewsDaoAsync) {

    fun saveData(filmList: List<NewsEntity>): Completable = Completable.fromCallable {
        newsDao.let {
            it.deleteAll()
            it.insertAll(filmList)
        }
    }.subscribeOn(Schedulers.io())

    fun getData(): Observable<List<NewsEntity>> = newsDaoAsync.getAll()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())

    fun clear(): Completable = Completable.fromAction {
        newsDao.deleteAll()
    }

    fun getNewsById(id: Int) = newsDaoAsync.getNewsById(id)
}