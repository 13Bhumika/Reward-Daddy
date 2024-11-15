package com.app.rewarddaddy.myreward.firebase

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.app.rewarddaddy.myreward.R
import com.app.rewarddaddy.myreward.activities.AvatarSelectionActivity
import com.app.rewarddaddy.myreward.activities.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class FirebaseAuthHelper(private val activity: Activity) {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var googleSignInClient: GoogleSignInClient

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(activity, gso)
    }

    fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account = task.result
            val idToken = account?.idToken
            if (idToken != null) {
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener(activity) { authTask ->
                        if (authTask.isSuccessful) {
                            val email = account.email
                            Toast.makeText(activity, "Sign-in successful for $email", Toast.LENGTH_SHORT).show()
                            val intent = Intent(activity, AvatarSelectionActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            activity.startActivity(intent)
                        } else {
                            Log.e("SignInError", "Authentication failed: ${authTask.exception?.message}", authTask.exception)
                            Toast.makeText(activity, "Sign-in failed: ${authTask.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Log.e("SignInError", "ID Token is null")
                Toast.makeText(activity, "Google sign-in failed: ID Token is null.", Toast.LENGTH_LONG).show()
            }
        } else {
            Log.e("SignInError", "Google sign-in failed: ${task.exception?.message}", task.exception)
            Toast.makeText(activity, "Google sign-in failed.", Toast.LENGTH_LONG).show()
        }
    }


    fun currentUserUID(): String {
        return auth.currentUser!!.uid
    }

    companion object {
        const val RC_SIGN_IN = 1001
    }
}
