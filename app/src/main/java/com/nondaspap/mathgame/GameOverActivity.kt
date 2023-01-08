package com.nondaspap.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GameOverActivity : AppCompatActivity() {

    private lateinit var playAgainButton: Button
    private lateinit var exitButton: Button
    private lateinit var resultScoreTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        initComponents()
        attatchListeners()
        showScore()
    }


    private fun initComponents() {

        this.exitButton = findViewById(R.id.exitButton)
        this.playAgainButton = findViewById(R.id.playAgainButton)
        this.resultScoreTextView = findViewById(R.id.resultTextView)
    }


    private fun attatchListeners() {

        exitButton.setOnClickListener {
            terminateApplication()
        }

        playAgainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showScore() {
        val totalScore = intent.getStringExtra("score")

        resultScoreTextView.text = "Your score is: $totalScore"
    }

    private fun terminateApplication() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}