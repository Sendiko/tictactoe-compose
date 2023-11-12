package com.sendiko.composetictactoe.ui.screens.home

sealed interface HomeScreenEvent {
    data class OnBoardClick(val boardData: BoardData) : HomeScreenEvent
    object OnResetGame : HomeScreenEvent
    object OnNewGame : HomeScreenEvent
    data class OnPlayerOneNameInput(val playerOneName: String): HomeScreenEvent
    data class OnPlayerTwoNameInput(val playerTwoName: String): HomeScreenEvent
    data class SetDialog(val dialog: Int): HomeScreenEvent
    data class SetShowDropDown(val isShowing: Boolean): HomeScreenEvent
}