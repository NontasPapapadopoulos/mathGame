package com.nondaspap.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var additionButton: Button
    lateinit var subtractionButton: Button
    lateinit var multiplicationButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        attatchListeners()
    }

    private fun initComponents() {
        this.additionButton = findViewById(R.id.additionButton)
        this.subtractionButton = findViewById(R.id.subtractionButton)
        this.multiplicationButton = findViewById(R.id.multiplicationButton)
    }


    private fun attatchListeners() {

        additionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("action", "addition")
            startActivity(intent)
        }

        subtractionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("action", "subtraction")
            startActivity(intent)
        }

        multiplicationButton.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("action", "multiplication")
            startActivity(intent)
        }

    }

}