package com.example.simpletranslator.core.di

import com.example.simpletranslator.core.TranslationApi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okhttpCallFactory: Lazy<Call.Factory>): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()
    }

    @Provides
    @Singleton
    fun provideTranslationApi(retrofit: Retrofit): TranslationApi {
        return retrofit.create(TranslationApi::class.java)
    }
}