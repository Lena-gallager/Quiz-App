package com.example.quizapi.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_TOKEN = "15a7z9KXhheHnQBxtRNoebt0X66QKKFTMxymar1P"
private const val BASE_URL = "https://quizapi.io/api/v1/questions/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface QuizApiService {
    @GET("?apiKey=$API_TOKEN")
    suspend fun getQuiz(): List<QuizModel>

    @GET("?apiKey=$API_TOKEN&")
    suspend fun getQuizByCategoryAndDifficulty(
        @Query("category") category: String,
        @Query("difficulty") difficulty: String
    ): List<QuizModel>
}

object QuizApi {
    val retrofitService: QuizApiService by lazy {
        retrofit.create(QuizApiService::class.java)
    }
}