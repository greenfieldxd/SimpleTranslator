package com.example.simpletranslator.core.domain.translation

import com.example.simpletranslator.core.data.network.TranslationApi
import com.example.simpletranslator.core.data.network.TranslationResponse
import com.example.simpletranslator.core.domain.LanguageCode
import javax.inject.Inject

interface TranslationUseCase {
    suspend fun translate(sl: LanguageCode, dl: LanguageCode, text: String) : TranslationResponse
}

class TranslationUseCaseImpl @Inject constructor(
private val translationApi: TranslationApi
) : TranslationUseCase {
    override suspend fun translate(sl: LanguageCode, dl: LanguageCode, text: String) : TranslationResponse {
        return translationApi.translate(sl.code, dl.code, text)
    }
}