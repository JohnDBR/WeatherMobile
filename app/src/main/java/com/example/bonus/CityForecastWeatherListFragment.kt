package com.example.bonus

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


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
                //if (elementDateTime.dayOfMonth.toInt() - currentDateTime.dayOfMonth.toInt() == 0) {

                //}
                var currentDateTime = LocalDateTime.parse(
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("y-M-d HH:mm:ss")),
                    DateTimeFormatter.ofPattern("y-M-d HH:mm:ss")
                )
                for (element in cityForecast) {
                    val elementDateTime = LocalDateTime.parse(
                        element.dt_txt,
                        DateTimeFormatter.ofPattern("y-M-d HH:mm:ss")
                    )

                    Log.d("PRVA",  "${currentDateTime.dayOfMonth} VS ${elementDateTime.dayOfMonth} | ${currentDateTime.hour} VS ${elementDateTime.hour}")

                    if (elementDateTime.dayOfMonth.toInt() - currentDateTime.dayOfMonth.toInt() == 0 || currentDateTime.hour == 0) {
                        Log.d("PRVA",  "true") //${elementDateTime.dayOfMonth.toInt() - currentDateTime.dayOfMonth.toInt()}")
                        val hourDifference = currentDateTime.hour - elementDateTime.hour
                        if (hourDifference in 0..3 || currentDateTime.hour in 1..3) {
                            Log.d("PRVA",  "true ${elementDateTime.hour - currentDateTime.hour}")
                            currentDateTime = currentDateTime.plusDays(1)
                            cityForecastWeatherModel.add(element)
                        } else {
                            Log.d("PRVA",  "false ${currentDateTime.hour - elementDateTime.hour}")
                        }
                    } else {
                        Log.d("PRVA",  "false") // ${elementDateTime.dayOfMonth.toInt() - currentDateTime.dayOfMonth.toInt()}")
                    }
                    //Log.d("PRVA",  "$currentDateTime VS $elementDateTime / $days | $hours | $minutes")
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
