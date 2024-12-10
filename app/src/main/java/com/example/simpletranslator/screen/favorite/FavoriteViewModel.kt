package com.example.simpletranslator.screen.favorite

import androidx.lifecycle.ViewModel
import com.example.simpletranslator.core.data.storage.favorite.TranslationFavorite
import com.example.simpletranslator.core.domain.favorite.GetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase
) : ViewModel() {
    fun getFavorite(): Flow<List<TranslationFavorite>> {
        return getFavoriteUseCase.invoke()
    }
}