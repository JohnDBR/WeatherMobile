package com.example.classvideos


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.bonus.ProfileRecyclerViewAdapter
import com.example.bonus.R
import com.example.bonus.databinding.FragmentItemListBinding
import com.example.bonus.models.RandomUser
import com.example.bonus.models.VolleySingleton

import com.example.classvideos.models.ProfileModel
import kotlinx.android.synthetic.main.fragment_item_list.view.*
import org.json.JSONArray
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment(), ProfileRecyclerViewAdapter.onListInteraction {

    val users = mutableListOf<ProfileModel>()
    private var adapter: ProfileRecyclerViewAdapter? = null
    var count: Int = 0
    lateinit var navController: NavController
    lateinit var mBinding: FragmentItemListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_list, container, false)
        val view = mBinding.root
        //for (i in 1..20) {
        //    users.add(
        //        ProfileModel(
        //            "brad",
        //            "gibson",
        //            R.drawable.banana,
        //            "https://randomuser.me/api/portraits/med/men/75.jpg"
        //        )
        //    )
        //    count++
        //}

        adapter = ProfileRecyclerViewAdapter(users, this)
        view.list.layoutManager = LinearLayoutManager(context)
        view.list.adapter = adapter
        VolleySingleton.getInstance(activity!!.applicationContext).addToRequestQueue(getJsonObjectRequest())
        //view.floatingActionButton.setOnClickListener() {
        //    users.add(ProfileModel("User "+count, "Profesor de Movil", R.drawable.banana))
        //    count++
        //    adapter!!.updateData()
        //}
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    //override fun onListButtonInteraction(item: ProfileModel?) {
    //    users.remove(item)
    //    adapter!!.updateData()
    //}

    override fun onListItemInteraction(item: ProfileModel?) {
        Log.d("John", "HOLA! ")
        val bundle = bundleOf("data" to item)
        navController!!.navigate(R.id.action_listFragment_to_detailFragment, bundle)
    }

    fun getJsonObjectRequest() : JsonObjectRequest {

        val url =  "https://randomuser.me/api/?results=20"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                //parseObject(response)
                parseObjectG(response)
            },
            Response.ErrorListener{
                Log.d("WebJson", "ERROR")

            }
        )
        return jsonObjectRequest
    }

    fun parseObjectG(response: JSONObject) {
        var list = RandomUser.getUser(response)
        for (element in list) {
            Log.d("WebJson", "parseObjectG " + element.name?.first)
            users.add(ProfileModel(element.name!!.first.toString(),element.name!!.last.toString(),R.drawable.banana,"https://randomuser.me/api/portraits/thumb/men/70.jpg"))
        }
        adapter!!.notifyDataSetChanged()
    }

}
