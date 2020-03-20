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
import com.example.bonus.databinding.FragmentCityCurrentWeatherListBinding
import com.example.bonus.models.CityCurrentWeatherViewModel

import com.example.bonus.models.CityCurrentWeatherModel
import com.example.bonus.utils.FormatUtil
import kotlinx.android.synthetic.main.fragment_city_current_weather_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class CityCurrentWeatherListFragment : Fragment(), CityCurrentWeatherRecyclerViewAdapter.onListInteraction {

    val cityCurrentWeatherModel = mutableListOf<CityCurrentWeatherModel>()
    private var adapter: CityCurrentWeatherRecyclerViewAdapter? = null
    var count: Int = 0
    lateinit var navController: NavController
    lateinit var mBinding: FragmentCityCurrentWeatherListBinding

    lateinit var viewModel : CityCurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_current_weather_list, container, false)
        val view = mBinding.root

        viewModel = ViewModelProvider(this).get(CityCurrentWeatherViewModel::class.java)
        viewModel.getCities().observe(viewLifecycleOwner, Observer { cities ->
            run {
                Log.d("PRVA",  "3")
                cityCurrentWeatherModel.clear()
                for (element in cities) {
                    cityCurrentWeatherModel.add(element)
                    Log.d("PRVA",  "citiesProfileModel.size = "+FormatUtil.getIconUrl(element.weather[0].icon))
                    Log.d("PRVA",  "citiesProfileModel.size = "+FormatUtil.getTemperatureFormat(element.main.feels_like, element.main.temp_min, element.main.temp_max))
                }
                Log.d("PRVA",  "citiesProfileModel.size = "+cityCurrentWeatherModel.size)
                adapter!!.notifyDataSetChanged()
            }
        })
        Log.d("PRVA",  "4")
        adapter = CityCurrentWeatherRecyclerViewAdapter(cityCurrentWeatherModel, this)
        view.cities_list.layoutManager = LinearLayoutManager(context)
        view.cities_list.adapter = adapter
        loadData()
        //view.floatingActionButton.setOnClickListener() {
        //    users.add(CityCurrentWeatherModel("User "+count, "Profesor de Movil", R.drawable.banana))
        //    count++
        //    adapter!!.updateData()
        //}
        return view
    }

    fun loadData() {
        cityCurrentWeatherModel.clear()
        viewModel.clearCities()
        adapter!!.notifyDataSetChanged()
        viewModel.addCity("Bogota")
        viewModel.addCity("Medellin")
        viewModel.addCity("Cali")
        viewModel.addCity("Barranquilla")
        viewModel.addCity("Cartagena")
        viewModel.addCity("Soacha")
        viewModel.addCity("Cucuta")
        viewModel.addCity("Soledad")
        viewModel.addCity("Bucaramanga")
        viewModel.addCity("Ibague")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    //override fun onListButtonInteraction(item: CityCurrentWeatherModel?) {
    //    users.remove(item)
    //    adapter!!.updateData()
    //}

    override fun onListItemInteraction(item: CityCurrentWeatherModel?) {
        val bundle = bundleOf("data" to item)
        navController!!.navigate(R.id.action_listFragment_to_detailFragment, bundle)
    }

}
