package com.example.simpletranslator.screen.history

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
import com.example.simpletranslator.core.data.storage.history.TranslationHistory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val historyList by viewModel.getHistory().collectAsState(initial = emptyList())

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(title = { Text("History") } )

        LazyColumn (modifier = Modifier.padding(horizontal = 16.dp)) {
           items(historyList) { history ->
               HistoryItem(history)
           }
        }
    }
}

@Composable
fun HistoryItem(history: TranslationHistory) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = "Source: ${history.sourceText}")
        Text(text = "Translation: ${history.translatedText}")

        // Use SimpleDateFormat with locale for compatibility with older Android versions
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formattedTimestamp = dateFormat.format(Date(history.timestamp))

        Text(text = "Timestamp: $formattedTimestamp")
    }
}

