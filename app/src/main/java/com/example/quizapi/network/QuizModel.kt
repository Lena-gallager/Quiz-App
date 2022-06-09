package com.example.quizapi.network

import com.squareup.moshi.Json

data class QuizModel(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "question") val question: String,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "answers") val answers: Map<String, String?>,
    @field:Json(name = "multiple_correct_answers") val multiple_correct_answers: String,
    @field:Json(name = "correct_answers") val correct_answers: Map<String, String>,
    @field:Json(name = "explanation") val explanation: String?,
    @field:Json(name = "category") val category: String,
    @field:Json(name = "difficulty") val difficulty: String
)