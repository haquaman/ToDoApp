package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.car_view_item_layout_list.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class Profile : Fragment() {
    // TODO: Rename and change types of parameters
    var currentUser : User = User(-1,"a","b","c","d")
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)
        view.button3
        val nameText = view.findViewById<TextView>(R.id.nameEditText)
        val surNameTExt = view.findViewById<TextView>(R.id.surNameEditText)
        val profileBtn = view.findViewById<Button>(R.id.savedProfileBtn)
        val logOutBtn = view.findViewById<Button>(R.id.logoutBtn)
        var arg = this.arguments
        var id = arg?.get("id")



        logOutBtn.setOnClickListener(){
            val intent = Intent(activity, LogUp::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            requireActivity().startActivity(intent)
        }


        //update Profile settings
        profileBtn.setOnClickListener(){
            val tempName = nameText.text.toString()
            val tempSurname = surNameTExt.text.toString()
            val updateDatabase = (activity as HomeAcivity).openOrCreateDatabase("Users", AppCompatActivity.MODE_PRIVATE,null)
            updateDatabase.execSQL("UPDATE users SET userName = ? WHERE id = ?", arrayOf(tempName,id))
            updateDatabase.execSQL("UPDATE users SET userSurName = ? WHERE id = ?", arrayOf(tempSurname,id))
            updateDatabase.close()
            val toast = Toast.makeText((activity as HomeAcivity), "Kullanıcı bilgileriniz güncellenmiştir", Toast.LENGTH_LONG)
            toast.show()
        }

        val database = (activity as HomeAcivity).openOrCreateDatabase("Users", AppCompatActivity.MODE_PRIVATE,null)
        val cursor = database.rawQuery("SELECT * FROM users WHERE id = $id",null)
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
            currentUser = User(id,n,sn,p,nick)
        }
        val strName = currentUser.userName
        nameText.text = currentUser.userName
        surNameTExt.text = currentUser.userSurName
        cursor.close()
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Profile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}