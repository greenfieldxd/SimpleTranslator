package com.example.simpletranslator.core.data.storage.history

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simpletranslator.core.data.storage.history.TranslationHistory.Companion.TRANSLATION_HISTORY_TABLE
import java.util.Date

@Entity(tableName = TRANSLATION_HISTORY_TABLE)
data class TranslationHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sourceText: String,
    val translatedText: String,
    val timestamp: Long = Date().time
) {
    companion object {
        const val TRANSLATION_HISTORY_TABLE = "TRANSLATION_HISTORY_TABLE"
    }
}