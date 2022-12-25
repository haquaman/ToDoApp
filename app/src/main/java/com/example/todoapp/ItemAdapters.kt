package com.example.todoapp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class ItemAdapters(var context: Context) : BaseAdapter() {

    var arrayList: ArrayList<ItemList> = ArrayList<ItemList>()

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return arrayList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    fun addItem(item: ItemList){
        arrayList.add(item)
    }


    fun  updategteView(){

    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view:View = View.inflate(context,R.layout.car_view_item_layout_list,null)

        var deleteBTn = view.findViewById<Button>(R.id.button3)
        deleteBTn.setOnClickListener(){ view ->

            var deletedUser: ItemList = getItem(p0) as ItemList
            var dtit = deletedUser.detail
            arrayList.removeAt(p0)
            var templist: ArrayList<ItemList> = ArrayList<ItemList>()
            val database = (context.applicationContext).openOrCreateDatabase("Tasks", AppCompatActivity.MODE_PRIVATE,null)
            database.execSQL("DELETE FROM  tasks WHERE detail = ?", arrayOf(dtit))
            templist = arrayList
            //notifyDataSetChanged()





        }

        var chbox = view.findViewById<CheckBox>(R.id.checkBox3)
        if((getItem(p0) as ItemList).status == "t"){
            chbox.isChecked = true
        }
        if((getItem(p0) as ItemList).status == "d"){
            chbox.isChecked = false
        }

        chbox.setOnClickListener(){
            if(chbox.isChecked == true){
                var deletedUser: ItemList = getItem(p0) as ItemList
                var dtit = deletedUser.detail
                val database = (context.applicationContext).openOrCreateDatabase("Tasks", AppCompatActivity.MODE_PRIVATE,null)
                database.execSQL("UPDATE tasks SET status = ? WHERE detail = ?", arrayOf("t",dtit))
                database.close()
            }
            else{
                var deletedUser: ItemList = getItem(p0) as ItemList
                var dtit = deletedUser.detail
                val database = (context.applicationContext).openOrCreateDatabase("Tasks", AppCompatActivity.MODE_PRIVATE,null)
                database.execSQL("UPDATE tasks SET status = ? WHERE detail = ?", arrayOf("d",dtit))
                database.close()
            }
        }
        var icons: ImageView = view.findViewById(R.id.icon_list)
        var title : TextView = view.findViewById(R.id.title_text_view)
        var category : TextView = view.findViewById(R.id.category_text_view)
        var details : TextView = view.findViewById(R.id.detail_text_view)
        var date : TextView = view.findViewById(R.id.date_text_view)

        var items: ItemList = arrayList.get(p0)

        title.text = items.title
        category.text = items.category
        details.text = items.detail
        date.text = items.date
        icons.setImageResource(items.icons!!)




        return view!!
    }
}