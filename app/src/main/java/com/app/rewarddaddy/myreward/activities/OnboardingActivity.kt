package com.app.rewarddaddy.myreward.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.rewarddaddy.myreward.R
import com.app.rewarddaddy.myreward.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private var binding: ActivityOnboardingBinding? = null
    private var clickedOnce = false

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnContinue?.setOnClickListener {
            if(clickedOnce) {
                startActivity(Intent(this@OnboardingActivity, AvatarSelectionActivity::class.java))
            }
            binding?.ivCircleOne?.setImageDrawable(getDrawable(R.drawable.circle_off))
            binding?.ivCircleTwo?.setImageDrawable(getDrawable(R.drawable.circle_on))
            binding?.ivOnboardingLogo?.setImageDrawable(getDrawable(R.drawable.ic_onboarding_logo_second))
            clickedOnce = true
        }

        binding?.tvSkip?.setOnClickListener {
            startActivity(Intent(this@OnboardingActivity, AvatarSelectionActivity::class.java))
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}