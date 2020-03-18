package com.example.bonus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bonus.databinding.FragmentCityCurrentWeatherItemBinding
import com.example.bonus.models.CityCurrentWeatherModel

class CityCurrentWeatherRecyclerViewAdapter(
    private val mValues: List<CityCurrentWeatherModel>,
    private val mListener: onListInteraction
    ) : RecyclerView.Adapter<CityCurrentWeatherRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CityCurrentWeatherRecyclerViewAdapter.ViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_city_current_weather_item, parent, false)
        //return ViewHolder(view)
        val binder: FragmentCityCurrentWeatherItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_city_current_weather_item, parent, false)
        return ViewHolder(binder)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(holder: CityCurrentWeatherRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = mValues[position]
        //holder.tFirstName.text = item.first_name
        //holder.tLastName.text = item.last_name
        holder.mView.card.setOnClickListener {
            mListener?.onListItemInteraction(item)
        }

        //holder.button.setOnClickListener({
        //    mListener?.onListButtonInteraction(item)
        //})

        holder.mView.user = item
        holder.mView.executePendingBindings()
    }

    //public fun updateData() {
    //    notifyDataSetChanged()
    //}

    inner class ViewHolder(val mView: FragmentCityCurrentWeatherItemBinding) : RecyclerView.ViewHolder(mView.root) {
        //val button: Button = mView.bDeleteUser
        //val tFirstName: TextView = mView.first_name
        //val tLastName: TextView = mView.last_name
    }

    public interface onListInteraction {
        fun onListItemInteraction(item: CityCurrentWeatherModel?)
    //    fun onListButtonInteraction(item: CityCurrentWeatherModel?)
    }
}