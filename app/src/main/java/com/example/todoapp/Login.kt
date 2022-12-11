package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun goToLogUpClick(view: View){
        val intent = Intent(applicationContext,LogUp::class.java)
        intent.putExtra("data","ikinci sayfa")
        startActivity(intent)
    }
}