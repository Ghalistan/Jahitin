package com.example.jahitin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = Firebase.auth.currentUser
        if (user == null) {
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, BerandaActivity::class.java)
            startActivity(intent)
        }
        this.finish()
    }
}