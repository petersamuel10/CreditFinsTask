package com.peter.creditfins.ui.viewState

import com.peter.creditfins.data.model.Movie

sealed class MainViewState {
    object Idle : MainViewState()
    object Loading : MainViewState()
    data class GetMovies(val movieList: List<Movie>) : MainViewState()
    data class Error(val error: String?) : MainViewState()
}
