package com.app.rewarddaddy.myreward.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.rewarddaddy.myreward.R
import com.app.rewarddaddy.myreward.databinding.ActivityAvatarSelectionBinding

class AvatarSelectionActivity : AppCompatActivity() {

    private var binding: ActivityAvatarSelectionBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = null
        setContentView(binding?.root)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}