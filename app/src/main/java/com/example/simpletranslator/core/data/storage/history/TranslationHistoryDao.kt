package com.example.simpletranslator.core.data.storage.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.simpletranslator.core.data.storage.history.TranslationHistory.Companion.TRANSLATION_HISTORY_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface TranslationHistoryDao {
    @Insert
    suspend fun insertHistory(history: TranslationHistory)

    @Query("SELECT * FROM $TRANSLATION_HISTORY_TABLE ORDER BY timestamp")
    fun getTranslationHistory(): Flow<List<TranslationHistory>>
}