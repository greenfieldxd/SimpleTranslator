package com.example.simpletranslator.core.domain.history

import com.example.simpletranslator.core.data.storage.history.TranslationHistory
import com.example.simpletranslator.core.data.storage.history.TranslationHistoryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetHistoryUseCase {
    operator fun invoke(): Flow<List<TranslationHistory>>
}

class GetHistoryUseCaseImpl @Inject constructor(
    private val translationHistoryDao: TranslationHistoryDao
) : GetHistoryUseCase {
    override fun invoke(): Flow<List<TranslationHistory>> {
        return translationHistoryDao.getTranslationHistory()
    }
}