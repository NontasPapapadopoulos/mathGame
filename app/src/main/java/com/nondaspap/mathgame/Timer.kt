package com.nondaspap.mathgame

import android.content.Context
import android.os.CountDownTimer
import java.util.*

class Timer(private val context: Context) {

    private val startTimerInMillis: Long = 20000
    var timeLeftInMillis: Long = startTimerInMillis
    lateinit var timer: CountDownTimer

    fun startTimeCounter() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTime()

            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                (context as GameActivity).decreasePlayersLifeWhenTimeIsUp()


            }

        }.start()

    }


    fun updateTime() {
        val remainingTime: Int = (timeLeftInMillis/1000).toInt()
        (context as GameActivity).minutesTextView.text = String.format(Locale.getDefault(), "%02d", remainingTime)
    }


    fun pauseTimer() {
        timer.cancel()
    }

    fun resetTimer() {
        timeLeftInMillis = startTimerInMillis
    }





}