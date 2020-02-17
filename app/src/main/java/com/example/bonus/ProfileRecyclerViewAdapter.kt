package com.example.bonus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.classvideos.models.ProfileModel
import kotlinx.android.synthetic.main.fragment_item.view.*

class ProfileRecyclerViewAdapter(
    private val mValues: List<ProfileModel>) : RecyclerView.Adapter<ProfileRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(holder: ProfileRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = mValues[position]
        holder.tTitle.text = item.title
        holder.tFirstName.text = item.first_name
        holder.tLastName.text = item.last_name
        holder.tEmail.text = item.email
        holder.tPhone.text = item.phone

        //holder.mView.setOnClickListener({
        //    mListener?.onListItemInteraction(item)
        //})

        //holder.button.setOnClickListener({
        //    mListener?.onListButtonInteraction(item)
        //})
    }

    //public fun updateData() {
    //    notifyDataSetChanged()
    //}

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        //val button: Button = mView.bDeleteUser
        val tTitle: TextView = mView.title
        val tFirstName: TextView = mView.first_name
        val tLastName: TextView = mView.last_name
        val tEmail: TextView = mView.email
        val tPhone: TextView = mView.phone
    }

    //interface onListInteraction {
    //    fun onListItemInteraction(item: ProfileModel?)
    //    fun onListButtonInteraction(item: ProfileModel?)
    //}
}