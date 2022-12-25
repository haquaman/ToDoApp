package com.example.todoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [List.newInstance] factory method to
 * create an instance of this fragment.
 */
class List : Fragment(), AdapterView.OnItemClickListener{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var itemAdapters:ItemAdapters ?= null
    private  var arrayList:ArrayList<ItemList> ?= null
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
        val view =  inflater.inflate(R.layout.fragment_list, container, false)
        val completed = view.findViewById<Button>(R.id.allTaskBtn2)
        var listView = view.findViewById<ListView>(R.id.card_view_list_view)
        completed.setOnClickListener(){
            var ar = listOf<String>("görev1").toTypedArray()
            val database2 = (activity as HomeAcivity).openOrCreateDatabase("Tasks", AppCompatActivity.MODE_PRIVATE,null)
            val cursor2 = database2.rawQuery("SELECT * FROM tasks WHERE title = ?",ar)
            val titlex = cursor2.getColumnIndex("title")
            val catx = cursor2.getColumnIndex("cat")
            val datex = cursor2.getColumnIndex("date")
            val statusx = cursor2.getColumnIndex("status")
            val detailx = cursor2.getColumnIndex("detail")
            val userx = cursor2.getColumnIndex("user")
            var arrayList2: ArrayList<ItemList> = ArrayList<ItemList>()
            itemAdapters = ItemAdapters(requireContext().applicationContext)
            listView?.adapter = itemAdapters
            listView?.onItemClickListener = this
            while(cursor2.moveToNext()) {
                val title = cursor2.getString(titlex)
                val cat = cursor2.getString(catx)
                val date = cursor2.getString(datex)
                val status = cursor2.getString(statusx)
                val detail = cursor2.getString(detailx)
                val user = cursor2.getInt(userx)
                val tempItem =
                    ItemList(R.drawable.taskicon, cat, title, "tesk", date, status, detail, user)
                arrayList2.add(tempItem)
                itemAdapters!!.arrayList = arrayList2

            }
            cursor2.close()
        }
        /*
        val text = view.findViewById<TextView>(R.id.homePageHeaderTextView)
        var user = "Hakan"
        text.setText("Merhaba $user")
        */
        var arg = this.arguments
        var id = arg?.get("id")
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
        cursor.close()
        //arrayList = ArrayList()
        //arrayList!!.add(ItemList(R.drawable.taskicon,"C2","Title2","task1","01/03/1999","devam ediyor","ilk görev datı",currentUser.id))
        val database2 = (activity as HomeAcivity).openOrCreateDatabase("Tasks", AppCompatActivity.MODE_PRIVATE,null)
        val cursor2 = database2.rawQuery("SELECT * FROM tasks",null)
        val titlex = cursor2.getColumnIndex("title")
        val catx = cursor2.getColumnIndex("cat")
        val datex = cursor2.getColumnIndex("date")
        val statusx = cursor2.getColumnIndex("status")
        val detailx = cursor2.getColumnIndex("detail")
        val userx = cursor2.getColumnIndex("user")

        itemAdapters = ItemAdapters(requireContext().applicationContext)
        listView?.adapter = itemAdapters
        listView?.onItemClickListener = this
        while(cursor2.moveToNext()) {
            val title = cursor2.getString(titlex)
            val cat = cursor2.getString(catx)
            val date = cursor2.getString(datex)
            val status = cursor2.getString(statusx)
            val detail = cursor2.getString(detailx)
            val user = cursor2.getInt(userx)
            val tempItem =
                ItemList(R.drawable.taskicon, cat, title, "tesk", date, status, detail, user)
            itemAdapters!!.arrayList!!.add(tempItem)

        }
        cursor2.close()

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment List.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            List().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }
}