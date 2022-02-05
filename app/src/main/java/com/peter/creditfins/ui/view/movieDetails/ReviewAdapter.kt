package com.peter.creditfins.ui.view.movieDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peter.creditfins.data.model.Review
import com.peter.creditfins.databinding.ItemMovieBinding
import com.peter.creditfins.databinding.ItemReviewBinding

typealias review = ArrayList<Review>

class ReviewAdapter :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    private var reviewList: review = arrayListOf()

    class ViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemReviewBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int = reviewList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.review = reviewList[position]
    }

    fun setData(movies: review) {
        this.reviewList.clear()
        this.reviewList.addAll(movies)
        notifyDataSetChanged()
    }
}