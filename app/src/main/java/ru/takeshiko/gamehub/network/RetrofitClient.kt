package ru.takeshiko.gamehub.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object for initializing and providing access to the Retrofit client and API service.
 * It configures the base URL, logging, and the API service for making network requests.
 */
object RetrofitClient {
    private const val BASE_URL = "https://api.igdb.com/v4/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    /**
     * The Retrofit API service instance for making requests to the IGDB API.
     */
    val apiService: IGDBApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IGDBApiService::class.java)
    }
}
