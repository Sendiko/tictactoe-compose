package com.sendiko.composetictactoe.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.sendiko.composetictactoe.ui.screens.home.HomeScreenState
import com.sendiko.composetictactoe.ui.screens.home.Players.PlayerOne
import com.sendiko.composetictactoe.ui.screens.home.Players.PlayerTwo

@Composable
fun Greetings(
    state: HomeScreenState
) {
    val playerTurnName = when (state.playerTurn) {
        PlayerOne -> state.playerOneName
        PlayerTwo -> state.playerTwoName
    }

    fun ifAllElementsAreSpace(array: MutableList<MutableList<Char>>): Boolean {
        for (row in array) {
            for (column in row) {
                if (column != ' ') {
                    return false
                }
            }
        }
        return true
    }

    val turnTextList = listOf(
        "It's $playerTurnName's turn",
        "Now it's $playerTurnName's again!",
        "Let's see what $playerTurnName does",
        "Is $playerTurnName gonna win?",
        "It's $playerTurnName again..."
    )
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = turnTextList.random(),
        style = TextStyle(
            textAlign = TextAlign.Start,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
    )
}