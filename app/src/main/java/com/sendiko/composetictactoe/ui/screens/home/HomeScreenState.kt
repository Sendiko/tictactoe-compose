package com.sendiko.composetictactoe.ui.screens.home

import com.sendiko.composetictactoe.ui.screens.home.Players.PlayerOne

data class HomeScreenState(
    val playerOne: String = "Player One",
    val playerTwo: String= "Player Two",
    val playerOneScore: Int = 0,
    val playerTwoScore: Int = 0,
    val isPlaying: Players = PlayerOne,
    val board: MutableList<MutableList<Char>> = mutableListOf(
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
    ),
)