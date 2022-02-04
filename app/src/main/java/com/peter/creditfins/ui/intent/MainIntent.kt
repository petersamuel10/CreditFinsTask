package com.peter.creditfins.ui.intent

sealed
class MainIntent {

    object GetMovies : MainIntent()
    class SetFav(val movieId:Int, val fav: Boolean) : MainIntent()

}