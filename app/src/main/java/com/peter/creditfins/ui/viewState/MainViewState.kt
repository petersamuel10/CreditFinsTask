package com.peter.creditfins.ui.viewState

import com.peter.creditfins.data.model.Movies

sealed class MainViewState {
    object Idle : MainViewState()
    object Loading : MainViewState()
    data class GetMovies(val movies: Movies) : MainViewState()
    data class Error(val error: String?) : MainViewState()
}
