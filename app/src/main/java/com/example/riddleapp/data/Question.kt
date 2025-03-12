package com.example.riddleapp.data

//Storing questions with options and correct answer
data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
