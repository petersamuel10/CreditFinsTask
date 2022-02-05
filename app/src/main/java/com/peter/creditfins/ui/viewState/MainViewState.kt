package com.peter.creditfins.ui.viewState

import com.peter.creditfins.data.model.Movie
import com.peter.creditfins.data.model.Review

sealed class MainViewState {
    object Idle : MainViewState()
    object Loading : MainViewState()
    data class GetMovies(val movieList: List<Movie>) : MainViewState()
    data class GetReview(val reviewList: List<Review>) : MainViewState()
    data class Error(val error: String?) : MainViewState()
}
