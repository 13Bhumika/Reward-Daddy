package com.app.rewarddaddy.myreward.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.rewarddaddy.myreward.databinding.ActivityCreateAccountBinding
import com.app.rewarddaddy.myreward.firebase.FirebaseAuthHelper

class CreateAccountActivity : AppCompatActivity() {

    private var binding: ActivityCreateAccountBinding? = null
    private lateinit var firebaseAuthHelper: FirebaseAuthHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        firebaseAuthHelper = FirebaseAuthHelper(this)

        binding?.btnSignInGoogle?.setOnClickListener {
            firebaseAuthHelper.signInWithGoogle()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}
