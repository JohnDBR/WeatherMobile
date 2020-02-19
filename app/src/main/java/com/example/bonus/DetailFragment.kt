package com.example.bonus


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.bonus.databinding.FragmentDetailBinding
import com.example.classvideos.models.ProfileModel

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() { //, View.OnClickListener {

    lateinit var navController: NavController
    lateinit var profileModel: ProfileModel
    lateinit var mBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_detail, container, false)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        profileModel = arguments!!.getParcelable("data")!!
        // mBinding = DataBindingUtil.setContentView(this.requireActivity(), R.layout.fragment_detail)
        mBinding.user = profileModel
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
