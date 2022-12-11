package com.example.todoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
        /*
        val text = view.findViewById<TextView>(R.id.homePageHeaderTextView)
        var user = "Hakan"
        text.setText("Merhaba $user")
        */
        var listView = view.findViewById<ListView>(R.id.card_view_list_view)
        arrayList = ArrayList()
        arrayList!!.add(ItemList(R.drawable.taskicon,"C1","Title1","task1","01/03/1999","devam ediyor","sdnflksjdanfkljasdnfkljsadnflkjsdnflkjnsadlkjfnsdlkjfnlksdjanfalkjsdnflkjsdnflkjasdnflkjnsadlkjfndsalkjnf lkjsdanfklajdsnfklj ndslfkjnsadlkfn aklsdjfnlk asdnflkjdsan flkjsdn lkfjdanslk fjansdlkjfn askldjn flksdjn flkjsdnfkljasdnfkljdasnfkljsdnfkljsdnfkljasdnfkljsad"))
        arrayList!!.add(ItemList(R.drawable.taskicon,"C2","Title2","task1","01/03/1999","devam ediyor","ilk görev datı"))
        arrayList!!.add(ItemList(R.drawable.taskicon,"C3","Title3","task1","01/03/1999","devam ediyor","ilk görev datı"))
        arrayList!!.add(ItemList(R.drawable.taskicon,"C4","Title4","task1","01/03/1999","devam ediyor","ilk görev datı"))
        arrayList!!.add(ItemList(R.drawable.taskicon,"C5","Title5","task1","01/03/1999","devam ediyor","ilk görev datı"))
        arrayList!!.add(ItemList(R.drawable.taskicon,"C6","Title6","task1","01/03/1999","devam ediyor","ilk görev datı"))
        arrayList!!.add(ItemList(R.drawable.taskicon,"C7","Title7","task1","01/03/1999","devam ediyor","ilk görev datı"))
        arrayList!!.add(ItemList(R.drawable.taskicon,"C8","Title8","task1","01/03/1999","devam ediyor","ilk görev datı"))
        arrayList!!.add(ItemList(R.drawable.taskicon,"C9","Title9","task1","01/03/1999","devam ediyor","ilk görev datı"))
        itemAdapters = ItemAdapters(requireContext().applicationContext,arrayList!!)
        listView?.adapter = itemAdapters
        listView?.onItemClickListener = this
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