package com.example.simpletranslator.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simpletranslator.core.data.storage.favorite.TranslationFavorite
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val favoriteList by viewModel.getFavorite().collectAsState(initial = emptyList())

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(title = { Text("Favorite") } )

        LazyColumn (modifier = Modifier.padding(horizontal = 16.dp)) {
            items(favoriteList) { favorite ->
                HistoryItem(favorite)
            }
        }
    }
}

@Composable
fun HistoryItem(favorite: TranslationFavorite) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Box {  }
        Text(text = "Source: ${favorite.sourceText}")
        Text(text = "Translation: ${favorite.translatedText}")

        // Use SimpleDateFormat with locale for compatibility with older Android versions
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formattedTimestamp = dateFormat.format(Date(favorite.timestamp))

        Text(text = "Timestamp: $formattedTimestamp")
    }
}