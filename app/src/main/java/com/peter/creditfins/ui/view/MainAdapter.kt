package com.peter.creditfins.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peter.creditfins.data.model.Movie
import com.peter.creditfins.databinding.ItemMovieBinding

typealias movies = ArrayList<Movie>

class MainAdapter() : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var movieList: movies = arrayListOf()

    class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.movie = movieList[position]
    }

    fun addData(movieList: movies) {
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

}