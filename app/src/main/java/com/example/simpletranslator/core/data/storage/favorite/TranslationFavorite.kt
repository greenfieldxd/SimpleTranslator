package com.example.simpletranslator.core.data.storage.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simpletranslator.core.data.storage.favorite.TranslationFavorite.Companion.TRANSLATION_FAVORITE_TABLE
import java.util.Date

@Entity(tableName = TRANSLATION_FAVORITE_TABLE)
data class TranslationFavorite(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sourceText: String,
    val translatedText: String,
    val timestamp: Long = Date().time
) {
    companion object {
        const val TRANSLATION_FAVORITE_TABLE = "TRANSLATION_FAVORITE_TABLE"
    }
}