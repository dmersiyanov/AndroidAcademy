package com.dmity.androidacademy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dmity.androidacademy.database.converter.DateConverter
import com.dmity.androidacademy.features.newsList.model.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun newsDaoAsync(): NewsDaoAsync

    companion object {

        private const val DB_NAME = "news_db"

        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
                }
            }
            return INSTANCE!!
        }

        fun destroyDataBase(){
            INSTANCE = null
        }

        fun getNewsDao(context: Context): NewsDao {
            return AppDatabase.getAppDataBase(context).newsDao()
        }

        fun getNewsDaoAsync(context: Context): NewsDaoAsync {
            return AppDatabase.getAppDataBase(context).newsDaoAsync()
        }
    }

}