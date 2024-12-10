package com.example.simpletranslator.screen.translation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslationScreen(
    viewModel: TranslationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(title = { Text("Language Translator") } )

        LanguageSelector(
            sourceLanguage = uiState.sourceLang,
            targetLanguage = uiState.targetLang,
            onSwapLanguages = { viewModel.swapLanguages() },
            onSelectSourceLanguage = { viewModel.selectSourceLanguage(it) },
            onSelectTargetLanguage = { viewModel.selectTargetLanguage(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextInput(
            language = uiState.sourceLang.title,
            text = uiState.inputText,
            onTextChanged = { viewModel.updateInputText(it) },
            onCleartext = { viewModel.clearInputText() },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TranslateButton(
            onTranslate = { viewModel.translateText() },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        uiState.translatedText?.let {
            var favorite by remember { mutableStateOf(false) }

            TranslationResult(
                sourceLanguage = uiState.sourceLang.title,
                result = it,
                modifier = Modifier.padding(horizontal = 16.dp),
                isFavorite = favorite,
                onClickToFavorite = {
                    if (!favorite) {
                        viewModel.saveFavorite()
                        favorite = true
                    }
                }
            )
        }
    }
}