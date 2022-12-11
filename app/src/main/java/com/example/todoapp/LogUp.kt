package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LogUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_up)
    }


    fun goToLogInClick(view: View){
        val intent = Intent(applicationContext,Login::class.java)
        intent.putExtra("data","ikinci sayfa")
        startActivity(intent)
    }
}