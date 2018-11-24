package com.dmity.androidacademy.database

import androidx.room.Dao
import androidx.room.Query
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface NewsDaoAsync {

    @Query("SELECT * FROM NewsEntity")
    fun getAll(): Observable<List<NewsEntity>>

    @Query("SELECT * FROM NewsEntity WHERE id = :id")
    fun getNewsById(id: Int): Single<NewsEntity>

}