package com.example.todoapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val database2 = (activity as HomeAcivity).openOrCreateDatabase("Tasks", AppCompatActivity.MODE_PRIVATE,null)
        //val cursor2 = database2.rawQuery("SELECT * FROM tasks WHERE title = ?",ar)
        val view =  inflater.inflate(R.layout.fragment_list, container, false)
        var arg = this.arguments
        var id = arg?.get("id")
        var listView = view.findViewById<ListView>(R.id.card_view_list_view)

        val devamEdenler = view.findViewById<Button>(R.id.allTaskBtn2)
        val allTaskOperation = view.findViewById<Button>(R.id.allTaskBtn)
        val completedOperation = view.findViewById<Button>(R.id.allTaskBtn3)
        val todayOperation = view.findViewById<Button>(R.id.allTaskBtn5)
        val doneOperation = view.findViewById<Button>(R.id.allTaskBtn6)


        doneOperation.setOnClickListener(){
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val formatted = current.format(formatter)

            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val nowTime = sdf.parse(formatted)
            var s5 = listOf<String>(id.toString()).toTypedArray()
            val database5 = (activity as HomeAcivity).openOrCreateDatabase("Tasks", AppCompatActivity.MODE_PRIVATE,null)
            val cursor5 = database5.rawQuery("SELECT * FROM tasks WHERE user = ?",s5)
            val titlex = cursor5.getColumnIndex("title")
            val catx = cursor5.getColumnIndex("cat")
            val datex = cursor5.getColumnIndex("date")
            val statusx = cursor5.getColumnIndex("status")
            val detailx = cursor5.getColumnIndex("detail")
            val userx = cursor5.getColumnIndex("user")
            var arrayList5: ArrayList<ItemList> = ArrayList<ItemList>()
            while(cursor5.moveToNext()) {
                val date = cursor5.getString(datex)
                val itemDate = sdf.parse(date)
                val cmp = nowTime.compareTo(itemDate)
                if(cmp > 0){
                    val title = cursor5.getString(titlex)
                    val cat = cursor5.getString(catx)
                    val status = cursor5.getString(statusx)
                    val detail = cursor5.getString(detailx)
                    val user = cursor5.getInt(userx)
                    val tempItem =
                        ItemList(R.drawable.taskicon, cat, title, "tesk", date, status, detail, user)
                    arrayList5.add(tempItem)
                }

            }
            itemAdapters = ItemAdapters(requireContext().applicationContext)
            listView?.adapter = itemAdapters
            listView?.onItemClickListener = this
            itemAdapters!!.arrayList = arrayList5
            cursor5.close()

        }

        todayOperation.setOnClickListener(){

            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val formatted = current.format(formatter)
            var s4 = listOf<String>(formatted,id.toString()).toTypedArray()
            val database4 = (activity as HomeAcivity).openOrCreateDatabase("Tasks", AppCompatActivity.MODE_PRIVATE,null)
            val cursor4 = database4.rawQuery("SELECT * FROM tasks WHERE date = ? AND user = ?",s4)
            val titlex = cursor4.getColumnIndex("title")
            val catx = cursor4.getColumnIndex("cat")
            val datex = cursor4.getColumnIndex("date")
            val statusx = cursor4.getColumnIndex("status")
            val detailx = cursor4.getColumnIndex("detail")
            val userx = cursor4.getColumnIndex("user")
            var arrayList4: ArrayList<ItemList> = ArrayList<ItemList>()
            while(cursor4.moveToNext()) {
                val title = cursor4.getString(titlex)
                val cat = cursor4.getString(catx)
                val date = cursor4.getString(datex)
                val status = cursor4.getString(statusx)
                val detail = cursor4.getString(detailx)
                val user = cursor4.getInt(userx)
                val tempItem =
                    ItemList(R.drawable.taskicon, cat, title, "tesk", date, status, detail, user)
                arrayList4.add(tempItem)
            }
            itemAdapters = ItemAdapters(requireContext().applicationContext)
            listView?.adapter = itemAdapters
            listView?.onItemClickListener = this
            itemAdapters!!.arrayList = arrayList4
            cursor4.close()

        }


        allTaskOperation.setOnClickListener(){
            var s1 = listOf<String>(id.toString()).toTypedArray()
            val database1 = (activity as HomeAcivity).openOrCreateDatabase("Tasks", AppCompatActivity.MODE_PRIVATE,null)
            val cursor1 = database1.rawQuery("SELECT * FROM tasks WHERE user = ?",s1)
            val titlex = cursor1.getColumnIndex("title")
            val catx = cursor1.getColumnIndex("cat")
            val datex = cursor1.getColumnIndex("date")
            val statusx = cursor1.getColumnIndex("status")
            val detailx = cursor1.getColumnIndex("detail")
            val userx = cursor1.getColumnIndex("user")
            var arrayList1: ArrayList<ItemList> = ArrayList<ItemList>()
            while(cursor1.moveToNext()) {
                val title = cursor1.getString(titlex)
                val cat = cursor1.getString(catx)
                val date = cursor1.getString(datex)
                val status = cursor1.getString(statusx)
                val detail = cursor1.getString(detailx)
                val user = cursor1.getInt(userx)
                val tempItem =
                    ItemList(R.drawable.taskicon, cat, title, "tesk", date, status, detail, user)
                arrayList1.add(tempItem)

            }

            itemAdapters = ItemAdapters(requireContext().applicationContext)
            listView?.adapter = itemAdapters
            listView?.onItemClickListener = this
            itemAdapters!!.arrayList = arrayList1
            cursor1.close()
        }
        devamEdenler.setOnClickListener(){
            var deS = listOf<String>("d",id.toString()).toTypedArray()
            val database2 = (activity as HomeAcivity).openOrCreateDatabase("Tasks", AppCompatActivity.MODE_PRIVATE,null)
            val cursor2 = database2.rawQuery("SELECT * FROM tasks WHERE status = ? AND user = ?",deS)
            val titlex = cursor2.getColumnIndex("title")
            val catx = cursor2.getColumnIndex("cat")
            val datex = cursor2.getColumnIndex("date")
            val statusx = cursor2.getColumnIndex("status")
            val detailx = cursor2.getColumnIndex("detail")
            val userx = cursor2.getColumnIndex("user")
            var arrayList21: ArrayList<ItemList> = ArrayList<ItemList>()
            while(cursor2.moveToNext()) {
                val title = cursor2.getString(titlex)
                val cat = cursor2.getString(catx)
                val date = cursor2.getString(datex)
                val status = cursor2.getString(statusx)
                val detail = cursor2.getString(detailx)
                val user = cursor2.getInt(userx)
                val tempItem =
                    ItemList(R.drawable.taskicon, cat, title, "tesk", date, status, detail, user)
                arrayList21.add(tempItem)
            }
            itemAdapters = ItemAdapters(requireContext().applicationContext)
            listView?.adapter = itemAdapters
            listView?.onItemClickListener = this
            itemAdapters!!.arrayList = arrayList21
            cursor2.close()
        }

        completedOperation.setOnClickListener(){
            var s3 = listOf<String>("t",id.toString()).toTypedArray()
            val database3 = (activity as HomeAcivity).openOrCreateDatabase("Tasks", AppCompatActivity.MODE_PRIVATE,null)
            val cursor3 = database3.rawQuery("SELECT * FROM tasks WHERE status = ? AND user = ?",s3)
            val titlex = cursor3.getColumnIndex("title")
            val catx = cursor3.getColumnIndex("cat")
            val datex = cursor3.getColumnIndex("date")
            val statusx = cursor3.getColumnIndex("status")
            val detailx = cursor3.getColumnIndex("detail")
            val userx = cursor3.getColumnIndex("user")
            var arrayList3: ArrayList<ItemList> = ArrayList<ItemList>()
            while(cursor3.moveToNext()) {
                val title = cursor3.getString(titlex)
                val cat = cursor3.getString(catx)
                val date = cursor3.getString(datex)
                val status = cursor3.getString(statusx)
                val detail = cursor3.getString(detailx)
                val user = cursor3.getInt(userx)
                val tempItem =
                    ItemList(R.drawable.taskicon, cat, title, "tesk", date, status, detail, user)
                arrayList3.add(tempItem)

            }

            itemAdapters = ItemAdapters(requireContext().applicationContext)
            listView?.adapter = itemAdapters
            listView?.onItemClickListener = this
            itemAdapters!!.arrayList = arrayList3
            cursor3.close()
        }
        /*
        val text = view.findViewById<TextView>(R.id.homePageHeaderTextView)
        var user = "Hakan"
        text.setText("Merhaba $user")
        */

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
        database2.execSQL("CREATE TABLE IF NOT EXISTS tasks (title VARCHAR, cat VARCHAR, date VARCHAR, status VARCHAR, detail VARCHAR, user INTEGER)")
        val cursor2 = database2.rawQuery("SELECT * FROM tasks",null)
        Log.e("sd","adsa")
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