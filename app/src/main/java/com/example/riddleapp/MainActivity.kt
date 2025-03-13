package com.example.riddleapp
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: RiddleViewModel
    private val buttons by lazy {
        listOf(
            findViewById<Button>(R.id.btnOption1),
            findViewById<Button>(R.id.btnOption2),
            findViewById<Button>(R.id.btnOption3),
            findViewById<Button>(R.id.btnOption4)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[RiddleViewModel::class.java]

        setupObservers()
        setupButtonListeners()

        viewModel.gameOver.observe(this) { isGameOver ->
            if (isGameOver) {
                val intent = Intent(this, GameOverActivity::class.java).apply {
                    putExtra("FINAL_SCORE", viewModel.score.value ?: 0) // Pasar el puntaje final
                }
                startActivity(intent)
                finish() // Cierra esta actividad para no volver atrás
            }
        }
    }

    private fun setupObservers() {
        viewModel.currentQuestion.observe(this) { question ->
            findViewById<TextView>(R.id.tvQuestion).text = question.questionText
            question.options.forEachIndexed { index, option ->
                buttons[index].text = option
            }
        }

        viewModel.score.observe(this) { score ->
            findViewById<TextView>(R.id.tvScore).text = "Score: $score"
        }
    }

    private fun setupButtonListeners() {
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                viewModel.checkAnswer(index)
            }
        }
    }
}