package com.sendiko.composetictactoe.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sendiko.composetictactoe.ui.screens.home.Players.PlayerOne
import com.sendiko.composetictactoe.ui.screens.home.Players.PlayerTwo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnBoardClick -> {
                _state.update {
                    val board = it.board
                    board[event.boardData.row][event.boardData.column] = event.boardData.letter
                    it.copy(
                        board = board,
                        isPlaying = when (state.value.isPlaying) {
                            PlayerOne -> PlayerTwo
                            PlayerTwo -> PlayerOne
                        }
                    )
                }
                Log.i("HomeScreenViewModel", "OnBoardClick: ${state.value}")
            }

            HomeScreenEvent.OnNewGame -> {
                _state.update {
                    it.copy(
                        playerOne = "Player One",
                        playerOneScore = 0,
                        playerTwo = "Player Two",
                        playerTwoScore = 0,
                        isPlaying = PlayerOne,
                        board = mutableListOf(
                            mutableListOf(' ', ' ', ' '),
                            mutableListOf(' ', ' ', ' '),
                            mutableListOf(' ', ' ', ' '),
                        )
                    )
                }
            }

            HomeScreenEvent.OnResetGame -> {
                _state.update {
                    it.copy(
                        isPlaying = PlayerOne,
                        board = mutableListOf(
                            mutableListOf(' ', ' ', ' '),
                            mutableListOf(' ', ' ', ' '),
                            mutableListOf(' ', ' ', ' '),
                        )
                    )
                }
            }
        }
    }
}