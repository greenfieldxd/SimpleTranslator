package com.example.simpletranslator.core.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.simpletranslator.core.data.storage.favorite.TranslationFavorite
import com.example.simpletranslator.core.data.storage.favorite.TranslationFavorite.Companion.TRANSLATION_FAVORITE_TABLE
import com.example.simpletranslator.core.data.storage.favorite.TranslationFavoriteDao
import com.example.simpletranslator.core.data.storage.history.TranslationHistory
import com.example.simpletranslator.core.data.storage.history.TranslationHistoryDao

@Database(entities = [TranslationHistory::class, TranslationFavorite::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun translationHistoryDao(): TranslationHistoryDao
    abstract fun translationFavoriteDao(): TranslationFavoriteDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()

                INSTANCE = instance
                instance
            }
        }

        private const val DATABASE_NAME = "DATABASE_NAME"
    }
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
                CREATE TABLE IF NOT EXIST $TRANSLATION_FAVORITE_TABLE(
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                sourceText TEXT NOT NULL,
                translatedText TEXT NOT NULL,
                timestamp INTEGER NOT NULL
                )
            """.trimIndent()
        )
    }
}