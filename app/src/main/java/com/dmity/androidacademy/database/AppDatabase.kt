package com.dmity.androidacademy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dmity.androidacademy.features.newsList.model.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {

        private const val DB_NAME = "news.db"

        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}