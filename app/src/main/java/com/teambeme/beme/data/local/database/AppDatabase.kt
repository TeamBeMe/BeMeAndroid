package com.teambeme.beme.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.teambeme.beme.data.local.dao.AnswerDao
import com.teambeme.beme.data.local.entity.AnswerData

@Database(entities = [AnswerData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val answerDao: AnswerDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "answer record database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}