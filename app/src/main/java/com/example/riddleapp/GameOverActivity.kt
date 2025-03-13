package com.example.riddleapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.riddleapp.databinding.ActivityGameOverBinding

class GameOverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameOverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout usando View Binding
        binding = ActivityGameOverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el puntaje final del Intent
        val finalScore = intent.getIntExtra("FINAL_SCORE", 0)
        binding.tvFinalScore.text = "Final Score: $finalScore" // Acceder al TextView usando binding

        // Configurar el botón "Play Again"
        binding.btnPlayAgain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Cierra esta actividad para no volver atrás
        }
    }
}