package com.nondaspap.mathgame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var scoreResultTextView: TextView
    private lateinit var lifesNumberTextView: TextView
    lateinit var minutesTextView:TextView

    private lateinit var questionTextView: TextView
    private lateinit var answerEditTextNumber: EditText

    private lateinit var okButton: Button
    private lateinit var nextButton: Button

    private var number1: Int = 0
    private var number2: Int = 0
    private var correctAnswer: Int = 0

    private var action: String = ""
    private var sign: String = ""

    private lateinit var timer: Timer




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        timer = Timer(this)

        initComponents()
        attatchListeners()
        getAction()
        setSign()
        generateQuestion()

        supportActionBar!!.title = action

    }



    private fun initComponents() {
        this.scoreResultTextView = findViewById(R.id.scoreResultTextView)
        this.lifesNumberTextView = findViewById(R.id.lifesNumberTextView)
        this.minutesTextView = findViewById(R.id.minutesTextView)

        this.nextButton = findViewById(R.id.nextButton)
        this.okButton = findViewById(R.id.okButton)

        this.questionTextView = findViewById(R.id.questionTextView)
        this.answerEditTextNumber = findViewById(R.id.answerEditTextNumber)
    }

    @SuppressLint("SetTextI18n")
    private fun attatchListeners() {

        okButton.setOnClickListener {

            timer.pauseTimer()

            if (answerEditTextNumber.text.isEmpty())
                Toast.makeText(applicationContext, "Please write an answer or click the next button", Toast.LENGTH_LONG).show()
            else {
                calculateAnswer()

                if (isAnswerCorrect()) {
                    scoreResultTextView.text = (scoreResultTextView.text.toString().toInt()  + 10).toString()
                    questionTextView.text = "Congratulations, your answer is correct"
                    clearInput()
                }

                else {
                    lifesNumberTextView.text = (lifesNumberTextView.text.toString().toInt() -1).toString()
                    questionTextView.text = "Sorry, your answer is wrong"
                    terminateGameWhenRunOutOfLives()
                }
            }
        }


        nextButton.setOnClickListener {
            timer.pauseTimer()
            timer.resetTimer()

            generateQuestion()
            clearInput()
        }

    }


    private fun generateQuestion() {
        number1 = Random.nextInt(0,100)
        number2 = Random.nextInt(0,100)

        questionTextView.text = "$number1 $sign $number2"

        timer.startTimeCounter()
    }

    private fun calculateAnswer() {
        when (action) {
            "addition" -> correctAnswer = number1 + number2
            "subtraction" -> correctAnswer = number1 - number2
            "multiplication" -> correctAnswer = number1 * number2
        }
    }

    private fun isAnswerCorrect(): Boolean {
        return correctAnswer == answerEditTextNumber.text.toString().toInt()
    }

    private fun getAction() {
        this.action = intent.getStringExtra("action").toString()
    }


    private fun setSign() {
        when (action) {
            "addition" -> sign = "+"
            "subtraction" -> sign = "-"
            "multiplication" -> sign = "*"
        }
    }

    private fun terminateGameWhenRunOutOfLives() {
        if (lifesNumberTextView.text.toString() == "0") {
            val intent = Intent(this@GameActivity, GameOverActivity::class.java)
            intent.putExtra("score", scoreResultTextView.text.toString())
            startActivity(intent)
            finish()
        }
    }

    private fun clearInput() {
        answerEditTextNumber.text.clear()
    }


    private fun resetTime() {
        timer.resetTimer()
        timer.updateTime()
    }

    fun decreasePlayersLifeWhenTimeIsUp() {
        lifesNumberTextView.text = (lifesNumberTextView.text.toString().toInt() - 1).toString()
        terminateGameWhenRunOutOfLives()
        resetTime()
        timer.startTimeCounter()
    }



}