package com.example.umniygorodhach.data.di

import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.BuildConfig
import com.example.umniygorodhach.data.remote.api.events.EventsApi
import com.example.umniygorodhach.data.remote.api.home.HomeApi
import com.example.umniygorodhach.data.remote.api.news.NewsApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import net.openid.appauth.AuthorizationService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    private val defaultJson = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

   /* @Singleton
    @Provides
    fun provideTokenInterceptor(
        authStateStorage: AuthStateStorage,
        authService: AuthorizationService
    ) = TokenInterceptor(authStateStorage, authService)
*/
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        //tokenInterceptor: TokenInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
           // .addInterceptor(tokenInterceptor)
            .build()


    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory = defaultJson.asConverterFactory("application/json".toMediaType())

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, converter: Converter.Factory): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URI)
            .client(client)
            .addConverterFactory(converter)
            .build()

    @Singleton
    @Provides
    fun provideResponseHandler() = ResponseHandler()

    @Singleton
    @Provides
    fun provideEventsApi(retrofit: Retrofit): EventsApi = retrofit.create()

    @Singleton
    @Provides
    fun provideHomeApi(retrofit: Retrofit): HomeApi = retrofit.create()

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create()

}