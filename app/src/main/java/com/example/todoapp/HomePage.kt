package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        var userName = "Hakan"
        val intent = intent
        var str = intent.getStringExtra("data")

        homePageHeaderTextView.text = "Merhaba inetnt"


    }
}