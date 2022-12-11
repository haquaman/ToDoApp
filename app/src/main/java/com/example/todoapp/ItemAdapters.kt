package com.example.todoapp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class ItemAdapters(var context: Context , var arrayList: ArrayList<ItemList>) : BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return arrayList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view:View = View.inflate(context,R.layout.car_view_item_layout_list,null)

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