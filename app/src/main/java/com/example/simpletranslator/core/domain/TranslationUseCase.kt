package com.example.simpletranslator.core.domain

import com.example.simpletranslator.core.TranslationApi
import javax.inject.Inject

class TranslationUseCase @Inject constructor(
    private val translationApi: TranslationApi
) {
    suspend fun translate() {

    }
}