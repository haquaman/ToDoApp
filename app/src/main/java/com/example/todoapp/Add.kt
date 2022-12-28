package com.example.todoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_add.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Add.newInstance] factory method to
 * create an instance of this fragment.
 */
class Add : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var itemAdapters:ItemAdapters ?= null
    var currentUser : User = User(-1,"a","b","c","d")

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
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_add, container, false)
        //data from activity
        var arg = this.arguments
        var id = arg?.get("id")
        //SQL for task
        val database = (activity as HomeAcivity).openOrCreateDatabase("Users", AppCompatActivity.MODE_PRIVATE,null)
        val cursor = database.rawQuery("SELECT * FROM users WHERE id = $id",null)
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
        cursor.close()

        // ADD Task
        view.allTaskBtn12.setOnClickListener {


            val tastTitle = view.findViewById<TextView>(R.id.editTextTextPersonName)
            val taskDate = view.findViewById<TextView>(R.id.editTextTextPersonName2)
            val taskDesc = view.findViewById<TextView>(R.id.editTextTextMultiLine)
            val taskCat = view.findViewById<TextView>(R.id.editTextTextPersonName4)

            val database = (activity as HomeAcivity).openOrCreateDatabase("Tasks", AppCompatActivity.MODE_PRIVATE,null)
            database.execSQL("CREATE TABLE IF NOT EXISTS tasks (idT INTEGER PRIMARY KEY, title VARCHAR, cat VARCHAR, date VARCHAR, status VARCHAR, detail VARCHAR, user INTEGER)")
            val sqlString = "INSERT INTO tasks (title, cat, date, status, detail, user) VALUES (?, ?, ?, ?, ?, ?)"
            val statement = database.compileStatement(sqlString)
            statement.bindString(1,tastTitle.text.toString())
            statement.bindString(2,taskCat.text.toString())
            statement.bindString(3,taskDate.text.toString())
            statement.bindString(4,"d")
            statement.bindString(5,taskDesc.text.toString())
            statement.bindString(6,currentUser.id.toString())
            statement.execute()


            tastTitle.text = ""
            taskDate.text = ""
            taskDesc.text = ""
            taskCat.text = ""

            //Add task toast message
            val toast = Toast.makeText((activity as HomeAcivity), "Listeye Yeni Görev Eklenmiştir", Toast.LENGTH_LONG)
            toast.show()


        }

        // Return the fragment view/layout
        return view

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Add.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Add().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun addTaskClick(view: View){

    }
}