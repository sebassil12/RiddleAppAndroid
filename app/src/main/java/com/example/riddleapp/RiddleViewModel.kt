package com.example.riddleapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riddleapp.data.Question

class RiddleViewModel:ViewModel() {
    private val questions = listOf(
        Question(
            "What's Android's official language?",
            listOf("Java", "Kotlin", "Python", "C++"),
            1
        ),
        Question(
            "What's the Android mascot?",
            listOf("Bear", "Droid", "Robot", "Kitty"),
            1
        )
    )
    private val _currentQuestion = MutableLiveData<Question>()
    val currentQuestion: LiveData<Question> = _currentQuestion

    private val _score = MutableLiveData(0)
    val score: LiveData<Int> = _score

    private var currentIndex = 0

    init {
        _currentQuestion.value = questions[currentIndex]
    }

    fun checkAnswer(selectedIndex: Int) {
        if (selectedIndex == currentQuestion.value?.correctAnswerIndex) {
            _score.value = _score.value?.plus(1)
        }
        nextQuestion()
    }

    private fun nextQuestion() {
        if (currentIndex < questions.size - 1) {
            currentIndex++
            _currentQuestion.value = questions[currentIndex]
        } else {
            // Game over - you could add more logic here
        }
    }
}