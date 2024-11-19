package com.example.simpletranslator.screen.translation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletranslator.core.domain.LanguageCode
import com.example.simpletranslator.core.domain.history.SaveHistoryUseCase
import com.example.simpletranslator.core.domain.translation.TranslationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslationViewModel @Inject constructor(
    private val translationUseCase: TranslationUseCase,
    private val saveHistoryUseCase: SaveHistoryUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(TranslationUiState())
    val uiState = _uiState.asStateFlow()

    fun updateInputText(text: String) {
        _uiState.update { it.copy(inputText = text) }
    }

    fun clearInputText() {
        _uiState.update {
            it.copy(inputText = "")
        }
    }

    fun translateText() {
        viewModelScope.launch {
            val result = translationUseCase.translate(
                sl = LanguageCode.ENGLISH,
                dl = LanguageCode.RUSSIAN,
                text = _uiState.value.inputText)

            _uiState.update { it.copy(translatedText = result.translations.possibleTranslations.firstOrNull() ?: it.inputText) }
            saveHistoryUseCase.invoke(_uiState.value.inputText, _uiState.value.translatedText.orEmpty())
        }
    }

    fun swapLanguages() {
        _uiState.update {
            it.copy(
                sourceLang = it.targetLang,
                targetLang = it.sourceLang
            )
        }
    }
}

data class TranslationUiState(
    val sourceLang: String = "English",
    val targetLang: String = "Russia",
    val inputText: String = "",
    val translatedText: String? = null
)