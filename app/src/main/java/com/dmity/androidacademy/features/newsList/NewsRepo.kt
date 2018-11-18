package com.dmity.androidacademy.features.newsList

import android.content.Context
import com.dmity.androidacademy.database.AppDatabase
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import io.reactivex.Completable
import io.reactivex.Single


class NewsRepo(private val context: Context) {

    fun saveData(filmList: List<NewsEntity>): Completable {
        return Completable.fromCallable {
            val db = AppDatabase.getAppDataBase(context)
            db?.newsDao()?.let {
                it.deleteAll()
                it.insertAll(filmList)
            }
        }
    }

    fun getData(): Single<List<NewsEntity>> {
        return Single.fromCallable {
            AppDatabase.getAppDataBase(context)?.newsDao()?.getAll()
        }
    }

}