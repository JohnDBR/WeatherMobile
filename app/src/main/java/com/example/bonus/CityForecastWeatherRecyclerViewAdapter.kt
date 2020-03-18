package com.example.bonus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bonus.databinding.FragmentCityCurrentWeatherItemBinding
import com.example.bonus.databinding.FragmentCityForecastWeatherItemBinding
import com.example.bonus.models.CityCurrentWeatherModel
import com.example.bonus.models.CityForecastWeatherModel

class CityForecastWeatherRecyclerViewAdapter(
    private val mValues: List<CityForecastWeatherModel>,
    private val mListener: onListInteraction
    ) : RecyclerView.Adapter<CityForecastWeatherRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CityForecastWeatherRecyclerViewAdapter.ViewHolder {
        val binder: FragmentCityForecastWeatherItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_city_forecast_weather_item, parent, false)
        return ViewHolder(binder)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(holder: CityForecastWeatherRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = mValues[position]

        //holder.mView.card.setOnClickListener {
        //    mListener?.onListItemInteraction(item)
        //}

        //holder.button.setOnClickListener({
        //    mListener?.onListButtonInteraction(item)
        //})

        holder.mView.city = item
        holder.mView.executePendingBindings()
    }

    inner class ViewHolder(val mView: FragmentCityForecastWeatherItemBinding) : RecyclerView.ViewHolder(mView.root) {
    }

    public interface onListInteraction {
        fun onListItemInteraction(item: CityCurrentWeatherModel?)
    //    fun onListButtonInteraction(item: CityCurrentWeatherModel?)
    }
}