package com.dmity.androidacademy.database

import androidx.room.*
import com.dmity.androidacademy.features.newsList.model.NewsEntity
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsEntity")
    fun getAll(): List<NewsEntity>

    @Query("SELECT * FROM NewsEntity WHERE id = :id")
    fun getNewsById(id: Int): NewsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(newsEntities: List<NewsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newsEntity: NewsEntity)

    @Delete
    fun delete(newsEntity: NewsEntity)

    @Query("DELETE FROM NewsEntity")
    fun deleteAll()

    @Query("SELECT * FROM NewsEntity")
    fun getAllObservable(): Observable<List<NewsEntity>>

    @Query("SELECT * FROM NewsEntity WHERE id = :id")
    fun getNewsByIdSingle(id: Int): Single<NewsEntity>

}