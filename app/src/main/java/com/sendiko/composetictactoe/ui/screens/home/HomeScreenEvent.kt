package com.sendiko.composetictactoe.ui.screens.home

sealed interface HomeScreenEvent {
    data class OnBoardClick(val boardData: BoardData) : HomeScreenEvent
    object OnResetGame : HomeScreenEvent
    object OnNewGame : HomeScreenEvent
}