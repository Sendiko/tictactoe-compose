package com.sendiko.composetictactoe.repository.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sendiko.composetictactoe.data.LocalRoom
import com.sendiko.composetictactoe.ui.screens.history.MatchData
import com.sendiko.composetictactoe.ui.screens.home.HomeScreenEvent
import com.sendiko.composetictactoe.ui.screens.home.HomeScreenState
import com.sendiko.composetictactoe.ui.screens.home.Players.PlayerOne
import com.sendiko.composetictactoe.ui.screens.home.Players.PlayerTwo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(app: Application) : ViewModel() {

    private val dao = LocalRoom.getInstance(app.applicationContext).dao

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    private fun checkForWin(board: MutableList<MutableList<Char>>): Boolean {
        for (i in 0..2) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][0] == board[i][2])
                return true
        }

        for (j in 0..2) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[0][j] == board[2][j])
                return true
        }

        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[0][0] == board[2][2])
            return true

        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[0][2] == board[2][0])
            return true

        return false
    }

    private fun isDraw(board: MutableList<MutableList<Char>>): Boolean {
        for (i in 0..2){
            for (j in 0..2){
                if (board[i][j] == ' '){
                    return false
                }
            }
        }
        return true
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnBoardClick -> {
                _state.update {
                    val board = it.board
                    board[event.boardData.row][event.boardData.column] = event.boardData.letter
                    it.copy(
                        board = board,
                        playerTurn = when (state.value.playerTurn) {
                            PlayerOne -> PlayerTwo
                            PlayerTwo -> PlayerOne
                        }
                    )
                }
                if (checkForWin(state.value.board)) {
                    _state.update {
                        it.copy(
                            isWinning = when(state.value.playerTurn){
                                PlayerOne -> PlayerTwo
                                PlayerTwo -> PlayerOne
                            },
                        )
                    }
                    when (state.value.isWinning) {
                        PlayerOne -> _state.update {
                            it.copy(
                                playerOneScore = it.playerOneScore + 1
                            )
                        }
                        PlayerTwo -> _state.update {
                            it.copy(
                                playerTwoScore = it.playerTwoScore + 1
                            )
                        }
                        null -> return
                    }
                } else if(isDraw(state.value.board)){
                    _state.update {
                        it.copy(
                            isDraw = true
                        )
                    }
                }
                Log.i("HomeScreenViewModel", "onBoardClick: ${state.value}")
            }

            HomeScreenEvent.OnNewGame -> {
                viewModelScope.launch {
                    dao.insertMatch(
                        matchData = MatchData(
                            playerOne = state.value.playerOneName,
                            playerOneScore = state.value.playerOneScore,
                            playerTwo = state.value.playerTwoName,
                            playerTwoScore = state.value.playerTwoScore
                        )
                    )
                }
                _state.update {
                    it.copy(
                        playerOneName = "",
                        playerOneScore = 0,
                        playerTwoName = "",
                        playerTwoScore = 0,
                        playerTurn = PlayerOne,
                        isWinning = null,
                        isDraw = false,
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
                        playerTurn = PlayerOne,
                        isWinning = null,
                        isDraw = false,
                        board = mutableListOf(
                            mutableListOf(' ', ' ', ' '),
                            mutableListOf(' ', ' ', ' '),
                            mutableListOf(' ', ' ', ' '),
                        )
                    )
                }
            }

            is HomeScreenEvent.OnPlayerOneNameInput -> _state.update {
                it.copy(playerOneName = event.playerOneName)
            }
            is HomeScreenEvent.OnPlayerTwoNameInput -> _state.update {
                it.copy(playerTwoName = event.playerTwoName)
            }
        }
    }
}