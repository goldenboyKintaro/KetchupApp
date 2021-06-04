package com.moblie.ketchupapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import java.util.*

object CustomBinds {

@JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImages(view: ImageView, url: String) {
        if (url.isNotEmpty()) {
            val circularProgressDrawable = CircularProgressDrawable(view.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(view)
                .load(url)
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .into(view)
        }
    }
}