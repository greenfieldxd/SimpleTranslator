package com.example.simpletranslator.core.domain.favorite

import com.example.simpletranslator.core.data.storage.favorite.TranslationFavorite
import com.example.simpletranslator.core.data.storage.favorite.TranslationFavoriteDao
import javax.inject.Inject

interface SaveFavoriteUseCase {
    suspend operator fun invoke(sourceText: String, translatedText: String)
}

class SaveFavoriteUseCaseImpl @Inject constructor(
    private val translationFavoriteDao: TranslationFavoriteDao
) : SaveFavoriteUseCase {
    override suspend fun invoke(sourceText: String, translatedText: String) {
        translationFavoriteDao.insertFavorite(
            TranslationFavorite(
                sourceText = sourceText,
                translatedText = translatedText
            )
        )
    }
}