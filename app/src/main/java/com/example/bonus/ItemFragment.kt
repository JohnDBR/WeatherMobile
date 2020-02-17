package com.example.classvideos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bonus.R
import com.example.classvideos.models.ProfileModel

/**
 * A simple [Fragment] subclass.
 */
class ItemFragment : Fragment() {//, View.OnClickListener {

    lateinit var profileModel: ProfileModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //navController = Navigation.findNavController(view)
        //profileModel = arguments!!.getParcelable("data")!!
        // mBinding = DataBindingUtil.setContentView(this.requireActivity(), R.layout.fragment_detail)
        //mBinding.user = profileModel
        //view.findViewById<Button>(R.id.bPlaces1).setOnClickListener(this)

        // This is not neccesary because we are using binding!
        // view.findViewById<TextView>(R.id.place_title).text = profileModel.title
    }

    //override fun onClick(v: View?) {
    //    when(v!!.id) {
    //        R.id.bPlaces1 -> {
    //            navController!!.navigate(R.id.action_detailFragment_to_listFragment)
    //        }
    //    }
    //}

}
