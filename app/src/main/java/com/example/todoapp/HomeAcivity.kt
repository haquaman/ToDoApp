package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home_acivity.*

class HomeAcivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_acivity)

        replaceFragment(Home())

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.ekle -> replaceFragment(Add())
                R.id.liste -> replaceFragment(List())
                R.id.profil -> replaceFragment(Profile())

                else ->{

                }



            }
            true
        }
    }


    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}