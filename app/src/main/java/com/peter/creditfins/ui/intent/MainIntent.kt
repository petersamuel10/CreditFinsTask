package com.peter.creditfins.ui.intent

sealed
class MainIntent {

    class GetMovies(val page: Int) : MainIntent()
    class SetFav(val movieId: Int, val fav: Boolean) : MainIntent()
    class GetReview(val movieId: Int, val page: Int) : MainIntent()

}