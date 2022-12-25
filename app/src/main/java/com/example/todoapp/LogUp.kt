package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.todoapp.databinding.ActivityLogUpBinding

class LogUp : AppCompatActivity() {

    private lateinit var binding : ActivityLogUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    fun goToLogInClick(view: View){
        val intent = Intent(applicationContext,Login::class.java)
        intent.putExtra("data","ikinci sayfa")
        startActivity(intent)
    }

    fun logUpClick(view: View){
        var name = binding.nameEditText2.text.toString()
        var surname = binding.surNameEditText2.text.toString()
        var pass = binding.logUpPassField.text.toString()
        var nick = binding.userNameEditText2.text.toString()

        try {
            val database = this.openOrCreateDatabase("Users", MODE_PRIVATE,null)
            database.execSQL("CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY, userName VARCHAR, userSurName VARCHAR, userPass VARCHAR, userNickName VARCHAR)")

            val sqlString = "INSERT INTO users (userName, userSurName, userPass, userNickName) VALUES (?, ?, ?, ?)"
            val statement = database.compileStatement(sqlString)
            statement.bindString(1,name)
            statement.bindString(2,surname)
            statement.bindString(3,pass)
            statement.bindString(4,nick)
            statement.execute()

        } catch (e: Exception){
            e.printStackTrace()
        }

        val intent = Intent(applicationContext,Login::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}