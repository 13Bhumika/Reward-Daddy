package com.app.rewarddaddy.myreward.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.app.rewarddaddy.myreward.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var binding: ActivitySplashBinding? = null
    private val totalTime = 4000L
    private val countDownInterval = 100L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val countDownTimer = object: CountDownTimer(totalTime, countDownInterval) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                val progress = ((totalTime - millisUntilFinished).toFloat() / totalTime * 100).toInt()
                binding?.tvProgress?.text = "$progress%"
                binding?.cpiMain?.progress = progress
            }

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, OnboardingActivity::class.java))
                finish()
            }

        }.start()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}