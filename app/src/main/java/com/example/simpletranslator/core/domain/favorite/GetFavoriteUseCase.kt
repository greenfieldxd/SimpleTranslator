package com.example.simpletranslator.core.domain.favorite

import com.example.simpletranslator.core.data.storage.favorite.TranslationFavorite
import com.example.simpletranslator.core.data.storage.favorite.TranslationFavoriteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetFavoriteUseCase {
    operator fun invoke(): Flow<List<TranslationFavorite>>
}

class GetFavoriteUseCaseImpl @Inject constructor(
    private val translationFavoriteDao: TranslationFavoriteDao
) : GetFavoriteUseCase {
    override fun invoke(): Flow<List<TranslationFavorite>> {
        return translationFavoriteDao.getTranslationFavorite()
    }
}