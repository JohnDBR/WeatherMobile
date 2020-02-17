package com.example.classvideos


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bonus.ProfileRecyclerViewAdapter
import com.example.bonus.R

import com.example.classvideos.models.ProfileModel
import kotlinx.android.synthetic.main.fragment_item_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {//, ProfileRecyclerViewAdapter.onListInteraction {

    val users = mutableListOf<ProfileModel>()
    private var adapter: ProfileRecyclerViewAdapter? = null
    var count: Int = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        for (i in 1..20) {
            users.add(
                ProfileModel(
                    "mr",
                    "brad",
                    "gibson",
                    "brad.gibson@example.com",
                    "011-962-7516"
                )
            )
        }
        adapter = ProfileRecyclerViewAdapter(users)
        view.list.layoutManager = LinearLayoutManager(context)
        view.list.adapter = adapter
        //view.floatingActionButton.setOnClickListener() {
        //    users.add(ProfileModel("User "+count, "Profesor de Movil", R.drawable.banana))
        //    count++
        //    adapter!!.updateData()
        //}
        return view
    }

    //override fun onListButtonInteraction(item: ProfileModel?) {
    //    users.remove(item)
    //    adapter!!.updateData()
    //}

    //override fun onListItemInteraction(item: ProfileModel?) {
    //    Log.d("John", "HOLA! "+item?.title)
    //}

}
