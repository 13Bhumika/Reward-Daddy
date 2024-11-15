package com.app.rewarddaddy.myreward.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.rewarddaddy.myreward.databinding.ActivityCreateAccountBinding
import com.app.rewarddaddy.myreward.firebase.FirebaseAuthHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FirebaseAuthHelper.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            firebaseAuthHelper.handleSignInResult(task)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}
