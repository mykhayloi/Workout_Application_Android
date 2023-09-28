package com.example.workout_application_android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface QuoteService {
    @GET("quotes")
    suspend fun getQuotes(): List<Quote>

    companion object {
        private const val baseUrl = "https://type.fit/api/"

        val client: QuoteService = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteService::class.java)
    }
}