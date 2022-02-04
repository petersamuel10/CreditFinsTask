package com.peter.creditfins.ui.view

import com.peter.creditfins.data.model.Movie

interface ClickListener {
    fun movieClickListener(actionType: ActionType, movie: Movie)
}

