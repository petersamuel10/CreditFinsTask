package com.peter.creditfins.util

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.peter.creditfins.R
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView).load("https://image.tmdb.org/t/p/original/$url")
        .placeholder(R.drawable.ic_movie).into(imageView)
}

@BindingAdapter("imageUrl")
fun setCircleImageUrl(imageView: CircleImageView, url: String?) {
    Glide.with(imageView).load("https://image.tmdb.org/t/p/original/$url")
        .placeholder(R.drawable.ic_user).into(imageView)
}

@BindingAdapter("rating")
fun setRating(ratingBar: RatingBar, rating: Double) {
    ratingBar.rating = rating.toFloat()/2
}