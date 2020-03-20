package com.example.bonus


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.bonus.databinding.FragmentCityCurrentWeatherItemBinding
import com.example.bonus.models.CityCurrentWeatherModel

/**
 * A simple [Fragment] subclass.
 */
class CityCurrentWeatherItemFragment : Fragment() {//, View.OnClickListener {

    lateinit var cityCurrentWeatherModel: CityCurrentWeatherModel
    lateinit var mBinding: FragmentCityCurrentWeatherItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_current_weather_item, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    //override fun onClick(v: View?) {
    //    when(v!!.id) {
    //        R.id.bPlaces1 -> {
    //            navController!!.navigate(R.id.action_detailFragment_to_listFragment)
    //        }
    //    }
    //}

}
