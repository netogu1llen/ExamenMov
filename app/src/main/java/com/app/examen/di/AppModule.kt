package com.app.examen.di

import android.content.Context
import com.app.examen.data.local.preferences.CountryPreferences
import com.app.examen.data.remote.api.CountryApi
import com.app.examen.data.repository.CountryRepositoryImpl
import com.app.examen.domain.repository.CountryRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    // BASE URL
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideCountryApi(retrofit: Retrofit): CountryApi = retrofit.create(CountryApi::class.java)

    @Provides
    @Singleton
    fun provideCountryPreferences(
        @ApplicationContext context: Context,
        gson: Gson,
    ): CountryPreferences = CountryPreferences(context, gson)

    @Provides
    @Singleton
    fun provideCountryRepository(
        api: CountryApi,
        preferences: CountryPreferences,
    ): CountryRepository = CountryRepositoryImpl(api, preferences)
}
