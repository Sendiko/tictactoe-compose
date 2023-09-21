package com.sendiko.composetictactoe.ui.screens.history

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HistoryViewModel: ViewModel() {

    private val _state = MutableStateFlow(HistoryScreenState())
    val state = _state.asStateFlow()

}