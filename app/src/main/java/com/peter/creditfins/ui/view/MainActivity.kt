package com.peter.creditfins.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.peter.creditfins.R
import com.peter.creditfins.base.BaseActivity
import com.peter.creditfins.data.model.Movies
import com.peter.creditfins.data.model.Result
import com.peter.creditfins.ui.intent.MainIntent
import com.peter.creditfins.ui.viewState.MainViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            mainViewModel.mainIntent.send(MainIntent.getMovies)
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
                        adapter.addData(it.movies.results as ArrayList<Result>)
                    }
                    is MainViewState.Error -> {
                        progressDialog.dismiss()
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    //region variable
    private val mainViewModel: MainViewModel by viewModels()
    private var adapter = MainAdapter()
    private lateinit var movies: Movies
// endregion
}