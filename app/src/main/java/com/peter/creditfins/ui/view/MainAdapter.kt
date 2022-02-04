package com.peter.creditfins.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peter.creditfins.data.model.Movie
import com.peter.creditfins.databinding.ItemMovieBinding

typealias movies = ArrayList<Movie>

class MainAdapter(private val actions: ClickListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var movieList: movies = arrayListOf()

    class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.movie = movieList[position]
        holder.binding.favBtn.setOnClickListener {
            movieList[position].fav = !movieList[position].fav
            actions.movieClickListener(ActionType.FAV, movieList[position])
            notifyItemChanged(position)
        }

    }

    fun setData(movies: movies) {
        this.movieList.clear()
        this.movieList.addAll(movies)
        notifyDataSetChanged()
    }

}