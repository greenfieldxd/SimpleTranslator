package com.example.simpletranslator.core.data.storage.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.simpletranslator.core.data.storage.favorite.TranslationFavorite.Companion.TRANSLATION_FAVORITE_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface TranslationFavoriteDao {
    @Insert
    suspend fun insertFavorite(favorite: TranslationFavorite)

    @Query("SELECT * FROM $TRANSLATION_FAVORITE_TABLE ORDER BY timestamp")
    fun getTranslationFavorite(): Flow<List<TranslationFavorite>>
}