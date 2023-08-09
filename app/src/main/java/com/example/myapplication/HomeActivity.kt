package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    var buttonLoginSignUp: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        buttonLoginSignUp = findViewById(R.id.loginSignUp)

        buttonLoginSignUp!!.setOnClickListener {
            val i = Intent(applicationContext, LoginSignUpActivity::class.java)
            startActivity(i)
        }
    }
}