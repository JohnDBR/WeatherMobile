package com.example.bonus


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bonus.databinding.FragmentItemListBinding
import com.example.bonus.models.RandomUserViewModel

import com.example.bonus.models.ProfileModel
import kotlinx.android.synthetic.main.fragment_item_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment(), ProfileRecyclerViewAdapter.onListInteraction {

    val usersProfileModel = mutableListOf<ProfileModel>()
    private var adapter: ProfileRecyclerViewAdapter? = null
    var count: Int = 0
    lateinit var navController: NavController
    lateinit var mBinding: FragmentItemListBinding

    lateinit var viewModel : RandomUserViewModel
    //private var userList = mutableListOf<ProfileModel>()

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

        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)
        viewModel.getUsers().observe(viewLifecycleOwner, Observer { users ->
            run {
                //userList = users as MutableList<ProfileModel> // We should be binding RandomUser class not ProfileModel anymore!
                //Log.d("VideoVolleyLiveData",  "userListSize "+userList.size)
                //for (element in users) {
                //     Log.d("WebJson", "View model observer is being executed!")
                //    usersProfileModel.add(
                //         ProfileModel(
                //             element.name!!.first.toString(),
                //             element.name!!.last.toString(),
                //             R.drawable.banana,element.picture!!.large.toString(),
                //             element.email.toString(),
                //             element.phone.toString()
                //         )
                //    )
                //}
                Log.d("PRVA",  "3")
                //usersProfileModel = users as MutableList<ProfileModel>
                usersProfileModel.clear()
                for (element in users) {
                    usersProfileModel.add(element)
                }
                Log.d("PRVA",  "usersProfileModel.size = "+usersProfileModel.size)
                adapter!!.notifyDataSetChanged()
            }
        })
        Log.d("PRVA",  "4")
        adapter = ProfileRecyclerViewAdapter(usersProfileModel, this)
        //for (element in usersProfileModel) {
        //    Log.d("PRVA",  "element "+element.name?.first)
        //}
        adapter!!.notifyDataSetChanged()
        view.list.layoutManager = LinearLayoutManager(context)
        view.list.adapter = adapter
        viewModel.addUser()
        //VolleySingleton.getInstance(activity!!.applicationContext).addToRequestQueue(getJsonObjectRequest())
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

}
