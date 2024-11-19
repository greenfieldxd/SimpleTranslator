package com.example.simpletranslator.core.di

import com.example.simpletranslator.core.data.network.TranslationApi
import com.example.simpletranslator.core.data.storage.history.TranslationHistoryDao
import com.example.simpletranslator.core.domain.history.GetHistoryUseCase
import com.example.simpletranslator.core.domain.history.GetHistoryUseCaseImpl
import com.example.simpletranslator.core.domain.history.SaveHistoryUseCase
import com.example.simpletranslator.core.domain.history.SaveHistoryUseCaseImpl
import com.example.simpletranslator.core.domain.translation.TranslationUseCase
import com.example.simpletranslator.core.domain.translation.TranslationUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideTranslationUseCase(translationApi: TranslationApi): TranslationUseCase {
        return TranslationUseCaseImpl(translationApi)
    }

    @Provides
    @Singleton
    fun provideSaveHistoryUseCase(translationHistoryDao: TranslationHistoryDao): SaveHistoryUseCase {
        return SaveHistoryUseCaseImpl(translationHistoryDao)
    }

    @Provides
    @Singleton
    fun provideGetHistoryUseCase(translationHistoryDao: TranslationHistoryDao): GetHistoryUseCase {
        return GetHistoryUseCaseImpl(translationHistoryDao)
    }
}