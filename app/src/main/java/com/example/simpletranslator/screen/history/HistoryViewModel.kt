package com.example.simpletranslator.screen.history

import androidx.lifecycle.ViewModel
import com.example.simpletranslator.core.data.storage.history.TranslationHistory
import com.example.simpletranslator.core.domain.history.GetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModel() {
    fun getHistory(): Flow<List<TranslationHistory>> {
        return getHistoryUseCase.invoke()
    }
}