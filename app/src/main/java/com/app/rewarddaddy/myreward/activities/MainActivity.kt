package com.app.rewarddaddy.myreward.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.app.rewarddaddy.myreward.R
import com.app.rewarddaddy.myreward.databinding.ActivityMainBinding
import io.adjoe.sdk.Adjoe
import io.adjoe.sdk.AdjoeException
import io.adjoe.sdk.AdjoeInitialisationListener
import io.adjoe.sdk.AdjoeNotInitializedException

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        Adjoe.init(this, "da38a1b86c7ed4c41c9f6eaaabe5becb", object: AdjoeInitialisationListener {

            override fun onInitialisationFinished() {
                Toast.makeText(
                    this@MainActivity,
                    "Success",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onInitialisationError(exception: Exception?) {
                Log.e("AdjoeError", exception!!.message.toString())
                Toast.makeText(
                    this@MainActivity,
                    "Failure",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


        binding?.play?.btnSains?.setOnClickListener {
            try {
                val adjoeOfferwallIntent = Adjoe.getOfferwallIntent(this@MainActivity)
                this@MainActivity.startActivity(adjoeOfferwallIntent)
            } catch(notInitializedException: AdjoeNotInitializedException) {
                // you have not initialized the adjoe SDK
            } catch(exception: AdjoeException) {
                // the offerwall cannot be displayed for some other reason
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}