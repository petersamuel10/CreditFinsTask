package com.peter.creditfins.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.peter.creditfins.R

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView).load("https://image.tmdb.org/t/p/original/$url")
        .placeholder(R.drawable.ic_movie).into(imageView)
}