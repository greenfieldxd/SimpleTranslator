package com.example.simpletranslator.core.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simpletranslator.core.data.storage.history.TranslationHistory
import com.example.simpletranslator.core.data.storage.history.TranslationHistoryDao

@Database(entities = [TranslationHistory::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun translationHistoryDao(): TranslationHistoryDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }

        private const val DATABASE_NAME = "DATABASE_NAME"
    }
}