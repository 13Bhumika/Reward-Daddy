package com.app.rewarddaddy.myreward.firebase

import android.content.Context
import android.widget.Toast
import com.app.rewarddaddy.myreward.activities.AvatarSelectionActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class Firestore (val context: Context) {
    val db = Firebase.firestore

    fun addUserData(UID: String, userName: String, selectedAvatar: Int, score: Double) {
        val user = hashMapOf(
            "username" to userName,
            "avatar" to selectedAvatar,
            "score" to score
        )

        db.collection("users")
            .document(UID)
            .set(user)
            .addOnSuccessListener {
                println("User data successfully written!")
            }
            .addOnFailureListener { e ->
                println("Error writing user data: ${e.message}")
            }
    }

    fun getUserData(UID: String, onResult: (Map<String, Any>?) -> Unit) {
        db.collection("users")
            .document(UID)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    // Return the user data as a map
                    onResult(document.data)
                } else {
                    // Document does not exist
                    onResult(null)
                }
            }
            .addOnFailureListener { e ->
                // Handle failure (e.g., log error)
                println("Error fetching user data: ${e.message}")
                onResult(null)
            }
    }


}