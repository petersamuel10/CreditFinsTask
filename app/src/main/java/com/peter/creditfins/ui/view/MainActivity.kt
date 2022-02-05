package com.peter.creditfins.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.peter.creditfins.R
import com.peter.creditfins.base.BaseActivity
import com.peter.creditfins.data.model.Movie
import com.peter.creditfins.databinding.ActivityMainBinding
import com.peter.creditfins.ui.intent.MainIntent
import com.peter.creditfins.ui.view.ActionType.*
import com.peter.creditfins.ui.view.movieDetails.MovieDetails
import com.peter.creditfins.ui.viewState.MainViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity(), ClickListener, PopupMenu.OnMenuItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        lifecycleScope.launch {
            mainViewModel.mainIntent.send(MainIntent.GetMovies)
        }

        binding.recyclerView.adapter = adapter
        binding.icFilter.setOnClickListener { showMenu(it) }

        observeViewModel()
    }

    private fun observeViewModel() {

        lifecycleScope.launch {

            mainViewModel.state.collect {
                when (it) {
                    is MainViewState.Idle -> {
                    }
                    is MainViewState.Loading -> {
                        progressDialog.show()
                    }
                    is MainViewState.GetMovies -> {
                        progressDialog.dismiss()
                        movieList.addAll(it.movieList)
                        adapter.setData(movieList)
                    }
                    is MainViewState.Error -> {
                        progressDialog.dismiss()
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun movieClickListener(actionType: ActionType, movie: Movie) {
        when (actionType) {
            FAV -> lifecycleScope.launch {
                mainViewModel.mainIntent.send(MainIntent.SetFav(movie.id, movie.fav))
            }

            SHOW_DETAILS -> {
                val intent = Intent(this, MovieDetails::class.java)
                intent.putExtra("movie", movie)
                startActivity(intent)
            }
        }
    }

    private fun showMenu(v: View) {
        PopupMenu(this, v).apply {
            setOnMenuItemClickListener(this@MainActivity)
            inflate(R.menu.filter_menu)
            show()
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.all -> {
                adapter.setData(movieList)
                true
            }
            R.id.favourite -> {
                val favList = movieList.filter { movie -> movie.fav } as ArrayList<Movie>
                adapter.setData(favList)
                true
            }
            else -> false
        }
    }

    //region variable
    private val mainViewModel: MainViewModel by viewModels()
    private var adapter = MainAdapter(this)
    private lateinit var binding: ActivityMainBinding
    private var movieList: ArrayList<Movie> = arrayListOf()
    // endregion
}