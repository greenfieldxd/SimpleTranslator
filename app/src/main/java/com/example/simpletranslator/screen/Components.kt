package com.example.simpletranslator.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextInput(language: String, text: String, onTextChanged: (String) -> Unit, onCleartext: () -> Unit) {
    Column {
        Text(text = language)
        OutlinedTextField(value = text, onValueChange = onTextChanged, modifier = Modifier.fillMaxWidth(), placeholder = {
            IconButton(onClick = onCleartext) {
                Icon(Icons.Default.Clear, contentDescription = "Clear text")
            }
        })
    }
}

@Composable
fun TranslateButton(onTranslate: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = onTranslate,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(top = 8.dp)
            ) {
                Text(text = "Translate")
        }
    }
}

@Composable
fun TranslationResult(result: String) {
    OutlinedTextField(
        value = result,
        onValueChange = {},
        readOnly = true,
        modifier = Modifier.fillMaxWidth()
    )
}