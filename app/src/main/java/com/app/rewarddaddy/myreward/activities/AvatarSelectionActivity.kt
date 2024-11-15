package com.app.rewarddaddy.myreward.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.rewarddaddy.myreward.R
import com.app.rewarddaddy.myreward.adapters.AvatarAdapter
import com.app.rewarddaddy.myreward.databinding.ActivityAvatarSelectionBinding
import com.app.rewarddaddy.myreward.firebase.FirebaseAuthHelper
import com.app.rewarddaddy.myreward.firebase.Firestore

class AvatarSelectionActivity : AppCompatActivity() {

    private var binding: ActivityAvatarSelectionBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var avatarAdapter: AvatarAdapter
    private var mSelectedAvatar = -1
    private var mUserName = ""
    private var mUID = ""
    private var mScore = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvatarSelectionBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupRecyclerView()

        binding?.btnGo?.setOnClickListener {
            if(!validateForm()) {
                Toast.makeText(
                    this@AvatarSelectionActivity,
                    "Add name and select avatar.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            mUserName = binding?.etName?.text.toString()
            mUID = FirebaseAuthHelper(this@AvatarSelectionActivity).currentUserUID()
            Firestore(this@AvatarSelectionActivity).addUserData(mUID, mUserName, mSelectedAvatar, mScore)
            startActivity(Intent(this@AvatarSelectionActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun validateForm(): Boolean {
        if(binding?.etName?.text?.isEmpty() == true)    return false
        else return mSelectedAvatar != -1
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.rv_avatar)

        val layoutManager = GridLayoutManager(this, 4)
        recyclerView.layoutManager = layoutManager

        val avatarImages = intArrayOf(
            R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avatar4,
            R.drawable.avatar5, R.drawable.avatar6, R.drawable.avatar7, R.drawable.avatar8,
            R.drawable.avatar9, R.drawable.avatar10, R.drawable.avatar11, R.drawable.avatar12
        )

        avatarAdapter = AvatarAdapter(avatarImages) { position ->
            val selectedAvatar = avatarImages[position]
            mSelectedAvatar = selectedAvatar
            val imageView: ImageView = binding?.ivAvatar as ImageView
            imageView.setImageDrawable(getDrawable(selectedAvatar))
        }
        recyclerView.adapter = avatarAdapter
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}