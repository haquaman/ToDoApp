package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home_acivity.*

class HomeAcivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_acivity)
        val intent = intent
        var name = intent.getStringExtra("currentUserName")
        var id = intent.getIntExtra("currentUserID",1)
        replaceFragment(Home(),id)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(Home(),id)
                R.id.ekle -> replaceFragment(Add(),id)
                R.id.liste -> replaceFragment(List(),id)
                R.id.profil -> replaceFragment(Profile(),id)

                else ->{

                }



            }
            true
        }
    }


    private fun replaceFragment(fragment: Fragment, id: Int){
        val mBundle = Bundle()
        mBundle.putInt("id",id)
        fragment.arguments = mBundle
        val fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}