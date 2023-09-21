package com.sendiko.composetictactoe.ui.screens.routes

sealed class Routes(val route: String){
    object HomeScreen: Routes("home_screen")
    object HistoryScreen: Routes("history_screen")
}
