package com.sendiko.composetictactoe.ui.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Yard
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.sendiko.composetictactoe.R
import com.sendiko.composetictactoe.ui.screens.home.BoardData
import com.sendiko.composetictactoe.ui.screens.home.Players

@Composable
fun BoardSquare(
    modifier: Modifier = Modifier,
    board: MutableList<MutableList<Char>>,
    row: Int,
    column: Int,
    playerTurn: Players,
    enabled: Boolean,
    onClick: (onBoardClick: BoardData) -> Unit,
) {
    Card(
        modifier = modifier
    ) {
        IconButton(
            modifier = Modifier.fillMaxSize(),
            enabled = enabled,
            onClick = {
                Log.i("BoardSquare", "onClick: $board")
                onClick(
                    BoardData(
                        row = row,
                        column = column,
                        letter = when (playerTurn) {
                            Players.PlayerOne -> 'X'
                            Players.PlayerTwo -> 'O'
                        }
                    )
                )
            }
        ) {
            if (board[row][column] == ' ') {
                Icon(
                    imageVector = Icons.Default.Yard,
                    contentDescription = "",
                    tint = Color.Transparent
                )
            } else {
                when (board[row][column]) {
                    'X' -> Icon(
                        painter = painterResource(id = R.drawable.x_icon),
                        contentDescription = "X",
                        tint = Color.Red
                    )

                    'O' -> Icon(
                        painter = painterResource(id = R.drawable.o_icon),
                        contentDescription = "O",
                        tint = Color.Blue
                    )
                }
            }
        }
    }
}