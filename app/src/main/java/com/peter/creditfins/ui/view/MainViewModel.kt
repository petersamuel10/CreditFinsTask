package com.peter.creditfins.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.creditfins.data.repository.MainRepository
import com.peter.creditfins.ui.intent.MainIntent
import com.peter.creditfins.ui.viewState.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    val mainIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val state: StateFlow<MainViewState>
        get() = _state


    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            mainIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.getMovies -> getMovies()
                }
            }
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            _state.value = MainViewState.Loading
            _state.value = try {
                MainViewState.GetMovies(repository.getMovieList())
            } catch (e: Exception) {
                MainViewState.Error(e.localizedMessage)
            }
        }
    }

}
