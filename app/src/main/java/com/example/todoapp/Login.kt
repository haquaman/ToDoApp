package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.todoapp.databinding.ActivityLogUpBinding
import com.example.todoapp.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun checkUser(): User ? {
        val strName = binding.loginName.text.toString()
        val strPass = binding.loginPass.text.toString()
        var ar = listOf<String>(strName,strPass).toTypedArray()
        val database = this.openOrCreateDatabase("Users", MODE_PRIVATE,null)
        val cursor = database.rawQuery("SELECT * FROM users WHERE userNickName = ? AND userPass = ?",ar)
        //(userName, userSurName, userPass, userNickName)
        val idx = cursor.getColumnIndex("id")
        val userNamex = cursor.getColumnIndex("userName")
        val userSurNamex = cursor.getColumnIndex("userSurName")
        val userPassx = cursor.getColumnIndex("userPass")
        val userNickNamex = cursor.getColumnIndex("userNickName")
        if(cursor.moveToNext()){
            val n = cursor.getString(userNamex)
            val id = cursor.getInt(idx)
            val sn = cursor.getString(userSurNamex)
            val p = cursor.getString(userPassx)
            val nick = cursor.getString(userNickNamex)
            val currentUser = User(id,n,sn,p,nick)
            val s = currentUser.userName
            val CSID = currentUser.id
            val toast = Toast.makeText(applicationContext, "$s adlı kullnıcının girişi gerçekleşti", Toast.LENGTH_LONG)
            toast.show()
            val intent = Intent(applicationContext,HomeAcivity::class.java)
            intent.putExtra("currentUserID",CSID)
            intent.putExtra("currentUserName",s)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            return currentUser
        }

        val toast = Toast.makeText(applicationContext, "Hatalı giriş yaptınız", Toast.LENGTH_SHORT)
        toast.show()
        return null
    }

    fun logInClick(view:View){
        checkUser()
    }

    fun goToLogUpClick(view: View){
        val intent = Intent(applicationContext,LogUp::class.java)
        intent.putExtra("data","ikinci sayfa")
        startActivity(intent)
    }
}