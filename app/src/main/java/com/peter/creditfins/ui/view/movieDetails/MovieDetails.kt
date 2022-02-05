package com.peter.creditfins.ui.view.movieDetails

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.peter.creditfins.R
import com.peter.creditfins.base.BaseActivity
import com.peter.creditfins.data.model.Movie
import com.peter.creditfins.data.model.Review
import com.peter.creditfins.databinding.ActivityMovieDetailsBinding
import com.peter.creditfins.ui.intent.MainIntent
import com.peter.creditfins.ui.view.MainViewModel
import com.peter.creditfins.ui.viewState.MainViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class MovieDetails : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
    }

    override fun onStart() {
        super.onStart()

        val movie = intent.getParcelableExtra<Movie>("movie")
        binding.movie = movie

        binding.detailBody.reviewRec.adapter = adapter

        lifecycleScope.launch {
            mainViewModel.mainIntent.send(MainIntent.GetReview(movie!!.id, reviewPage))
        }

        binding.detailBody.favBtn.setOnClickListener {
            lifecycleScope.launch {
                movie!!.fav = !movie.fav
                binding.movie = movie
                mainViewModel.mainIntent.send(MainIntent.SetFav(movie.id, movie.fav))
            }
        }

        binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

            val percentage: Float =
                abs(verticalOffset).toFloat() / appBarLayout.totalScrollRange
            when {
                verticalOffset == 0 -> binding.detailHeader.headerLy.alpha = 1.0f
                abs(verticalOffset) >= appBarLayout.totalScrollRange -> binding.detailHeader.headerLy.alpha =
                    1 - percentage
            }

        })

        binding.detailBody.reviewRec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    loadMore = true
                    lifecycleScope.launch {
                        mainViewModel.mainIntent.send(
                            MainIntent.GetReview(
                                movie!!.id,
                                ++reviewPage
                            )
                        )
                    }
                }
            }
        })
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
                    is MainViewState.GetReview -> {
                        progressDialog.dismiss()
                        adapter.setData(loadMore, it.reviewList as ArrayList<Review>)
                    }
                    is MainViewState.Error -> {
                        progressDialog.dismiss()
                        Toast.makeText(this@MovieDetails, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    //region variable
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMovieDetailsBinding
    private var adapter = ReviewAdapter()
    private var reviewPage = 1
    private var loadMore = false
    // endregion
}