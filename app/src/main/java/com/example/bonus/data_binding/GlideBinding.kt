package com.example.bonus.data_binding

import com.bumptech.glide.Glide
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import androidx.databinding.BindingAdapter
import com.example.bonus.R

class GlideBinding {

    companion object {
        @JvmStatic
        @BindingAdapter("imageResource")
        fun setImageResource(view: ImageView, imageUrl: Int) { //String

            val context = view.getContext()

            val option = RequestOptions()
                .placeholder(R.drawable.banana)
                .error(R.drawable.banana)

            Glide.with(context)
                .setDefaultRequestOptions(option)
                .load(imageUrl)
                .into(view)

        }

        @JvmStatic
        @BindingAdapter("imageResourceUrl")
        fun setImageResourceUrl(view: ImageView, imageUrl: String) { //String

            val context = view.getContext()

            val option = RequestOptions()
                .placeholder(R.drawable.banana)
                .error(R.drawable.banana)

            Glide.with(context)
                .setDefaultRequestOptions(option)
                .load(imageUrl)
                .into(view)

        }
    }
}