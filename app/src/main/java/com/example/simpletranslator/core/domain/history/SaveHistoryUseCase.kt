package com.example.simpletranslator.core.domain.history

import com.example.simpletranslator.core.data.storage.history.TranslationHistory
import com.example.simpletranslator.core.data.storage.history.TranslationHistoryDao
import javax.inject.Inject

interface SaveHistoryUseCase {
    suspend operator fun invoke(sourceText: String, translatedText: String)
}

class SaveHistoryUseCaseImpl @Inject constructor(
    private val translationHistoryDao: TranslationHistoryDao
) : SaveHistoryUseCase {
    override suspend fun invoke(sourceText: String, translatedText: String) {
        translationHistoryDao.insertHistory(
            TranslationHistory(
                sourceText = sourceText,
                translatedText = translatedText
            )
        )
    }
}