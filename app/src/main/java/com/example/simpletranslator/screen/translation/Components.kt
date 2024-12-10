package com.example.simpletranslator.screen.translation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.simpletranslator.R
import com.example.simpletranslator.core.domain.LanguageCode

@Composable
fun TextInput(language: String, text: String, onTextChanged: (String) -> Unit, onCleartext: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = language)
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Enter text here")
            },
            trailingIcon = {
                IconButton(onClick = onCleartext) {
                    Icon(Icons.Default.Clear, contentDescription = "Clear text")
                }
            }
        )
    }
}

@Composable
fun TranslateButton(onTranslate: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth()) {
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
fun TranslationResult(sourceLanguage:String, result: String, isFavorite: Boolean, onClickToFavorite: () -> Unit, modifier: Modifier = Modifier) {

    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row {
            Column (modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(text = sourceLanguage, style = MaterialTheme.typography.headlineSmall)
                Text(text = result)
                IconButton(onClick = onClickToFavorite, modifier = Modifier.align(Alignment.End)) {
                    val icon =
                        if (isFavorite) ImageVector.vectorResource(R.drawable.ic_star_on)
                        else ImageVector.vectorResource(R.drawable.ic_star_off)
                    Icon(imageVector = icon, contentDescription = "favorite")
                }
            }
        }
    }
}

@Composable
fun LanguageSelector(
    modifier: Modifier = Modifier,
    sourceLanguage: LanguageCode,
    targetLanguage: LanguageCode,
    onSwapLanguages: () -> Unit,
    onSelectSourceLanguage: (LanguageCode) -> Unit,
    onSelectTargetLanguage: (LanguageCode) -> Unit
) {

    var isLeftIconSelected by remember { mutableStateOf(false) }
    var showLanguageDialog by remember { mutableStateOf(false) }

    Row (
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FlagIcon(
            iconRes = sourceLanguage.flagIconRes,
            onClick = {
                isLeftIconSelected = true
                showLanguageDialog = true
            }
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_swap),
            contentDescription = "Swap",
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onSwapLanguages)
        )

        FlagIcon(
            iconRes = targetLanguage.flagIconRes,
            onClick = {
                isLeftIconSelected = false
                showLanguageDialog = true
            }
        )
    }

    if (showLanguageDialog) {
        LanguageSelectionDialog(
            languages = LanguageCode.entries,
            onLanguageSelected = {
                if (isLeftIconSelected) {
                    onSelectSourceLanguage(it)
                } else {
                    onSelectTargetLanguage(it)
                }
            },
            onDismiss = { showLanguageDialog = false }
        )
    }
}

@Composable
fun FlagIcon(modifier: Modifier = Modifier, iconRes: Int, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .size(40.dp)
            .background(color = MaterialTheme.colorScheme.surface, shape = CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = "Flag",
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun LanguageSelectionDialog(
    languages: List<LanguageCode>,
    onLanguageSelected: (LanguageCode) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        title = { Text("Select Language") },
        text = {
            Column {
                languages.forEach { language ->
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLanguageSelected(language) }
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = language.flagIconRes),
                            contentDescription = language.title,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = language.title,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        },
        shape = RoundedCornerShape(16.dp)
    )
}