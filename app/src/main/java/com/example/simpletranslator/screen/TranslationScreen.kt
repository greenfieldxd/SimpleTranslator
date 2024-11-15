package com.example.simpletranslator.screen

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
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(title = { Text("Simple Translator") } )

        TextInput(
            language = uiState.sourceLang,
            text = uiState.inputText,
            onTextChanged = { viewModel.updateInputText(it) },
            onCleartext = { viewModel.clearInputText() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TranslateButton(onTranslate = { viewModel.translateText() })

        uiState.translateText?.let {
            TranslationResult(it)
        }
    }
}