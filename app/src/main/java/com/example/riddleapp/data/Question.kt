package com.example.riddleapp.data

//Storing questions with options and correct answer
data class Question(
    val text:String,
    val options: List<String>,
    val correctAnswer: String,
)
