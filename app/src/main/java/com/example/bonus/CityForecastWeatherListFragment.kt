package com.example.bonus

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bonus.databinding.FragmentCityForecastWeatherListBinding
import com.example.bonus.models.CityCurrentWeatherModel
import com.example.bonus.models.CityForecastWeatherModel
import com.example.bonus.models.CityForecastWeatherViewModel
import kotlinx.android.synthetic.main.fragment_city_forecast_weather_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class CityForecastWeatherListFragment : Fragment(), CityForecastWeatherRecyclerViewAdapter.onListInteraction { //, View.OnClickListener {

    lateinit var navController: NavController
    lateinit var cityCurrentWeatherModel: CityCurrentWeatherModel
    val cityForecastWeatherModel = mutableListOf<CityForecastWeatherModel>()
    private var adapter: CityForecastWeatherRecyclerViewAdapter? = null
    var count: Int = 0
    lateinit var viewModel : CityForecastWeatherViewModel
    lateinit var mBinding: FragmentCityForecastWeatherListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_forecast_weather_list, container, false)
        val view = mBinding.root

        viewModel = ViewModelProvider(this).get(CityForecastWeatherViewModel::class.java)
        viewModel.getCityForecast().observe(viewLifecycleOwner, Observer { cityForecast ->
            run {
                Log.d("PRVA",  "3")
                cityForecastWeatherModel.clear()
                for (element in cityForecast) {
                    cityForecastWeatherModel.add(element)
                }
                Log.d("PRVA",  "citiesProfileModel.size = "+cityForecastWeatherModel.size)
                adapter!!.notifyDataSetChanged()
            }
        })
        Log.d("PRVA",  "4")
        adapter = CityForecastWeatherRecyclerViewAdapter(cityForecastWeatherModel, this)
        adapter!!.notifyDataSetChanged()
        view.forecast_list.layoutManager = LinearLayoutManager(context)
        view.forecast_list.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        cityCurrentWeatherModel = arguments!!.getParcelable("data")!!
        viewModel.addCity(cityCurrentWeatherModel.hard_name)
        adapter!!.notifyDataSetChanged()
        mBinding.city = cityCurrentWeatherModel //cityForecastWeatherModel
    }

    //override fun onClick(v: View?) {
    //    when(v!!.id) {
    //        R.id.bPlaces1 -> {
    //            navController!!.navigate(R.id.action_detailFragment_to_listFragment)
    //        }
    //    }
    //}

    override fun onListItemInteraction(item: CityCurrentWeatherModel?) {
    }

}
