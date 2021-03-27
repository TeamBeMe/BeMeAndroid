package com.teambeme.beme.di

import android.content.Context
import androidx.room.Room
import com.teambeme.beme.data.local.dao.AnswerDao
import com.teambeme.beme.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "answer record database"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideAnswerDao(database: AppDatabase): AnswerDao =
        database.answerDao
}