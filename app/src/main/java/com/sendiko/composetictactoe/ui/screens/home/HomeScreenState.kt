package com.sendiko.composetictactoe.ui.screens.home

import com.sendiko.composetictactoe.ui.screens.home.Players.PlayerOne

data class HomeScreenState(
    val playerOneName: String = "",
    val playerTwoName: String= "",
    val playerOneScore: Int = 0,
    val playerTwoScore: Int = 0,
    val playerTurn: Players = PlayerOne,
    val isWinning: Players?= null,
    val isDraw: Boolean = false,
    val board: MutableList<MutableList<Char>> = mutableListOf(
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
    ),
)