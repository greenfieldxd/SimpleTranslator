package com.example.simpletranslator.core.di

import android.content.Context
import com.example.simpletranslator.core.data.storage.AppDatabase
import com.example.simpletranslator.core.data.storage.history.TranslationHistoryDao
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
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideHistoryDao(db: AppDatabase): TranslationHistoryDao {
        return db.translationHistoryDao()
    }
}