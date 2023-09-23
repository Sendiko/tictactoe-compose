package com.sendiko.composetictactoe.repository.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.composetictactoe.data.LocalRoom
import com.sendiko.composetictactoe.ui.screens.history.HistoryScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(app: Application) : ViewModel() {

    private val dao = LocalRoom.getInstance(app.applicationContext).dao

    private val _state = MutableStateFlow(HistoryScreenState())
    private val matchHistory = dao.getMatches()

    val state = combine(_state, matchHistory){ state, matchHistory ->
        state.copy(
            matchHistory = matchHistory
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HistoryScreenState())


}