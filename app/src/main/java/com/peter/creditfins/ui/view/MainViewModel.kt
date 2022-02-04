package com.peter.creditfins.ui.view

import androidx.lifecycle.ViewModel
import com.peter.creditFins.data.repository.MainRepository
import com.peter.creditFins.ui.intent.MainIntent
import com.peter.creditFins.ui.viewState.MainViewState
import com.peter.creditfins.util.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val repository: MainRepository,
) : ViewModel() {

    val mainIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val state: StateFlow<MainViewState>
        get() = _state

}
