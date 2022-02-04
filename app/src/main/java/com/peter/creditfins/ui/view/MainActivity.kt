package com.peter.creditfins.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.peter.creditfins.R
import com.peter.creditfins.base.BaseActivity
import com.peter.creditfins.data.model.Movie
import com.peter.creditfins.ui.intent.MainIntent
import com.peter.creditfins.ui.view.ActionType.*
import com.peter.creditfins.ui.viewState.MainViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity(), ClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            mainViewModel.mainIntent.send(MainIntent.GetMovies)
        }

        recyclerView.adapter = adapter

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
                        adapter.addData(it.movieList as ArrayList<Movie>)
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

            }
        }
    }

    //region variable
    private val mainViewModel: MainViewModel by viewModels()
    private var adapter = MainAdapter(this)
    // endregion
}