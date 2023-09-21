package com.sendiko.composetictactoe.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sendiko.composetictactoe.ui.components.BoardSquare
import com.sendiko.composetictactoe.ui.components.Greetings
import com.sendiko.composetictactoe.ui.components.ScoreBoard
import com.sendiko.composetictactoe.ui.screens.history.MatchData
import com.sendiko.composetictactoe.ui.screens.routes.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeScreenState,
    onEvent: (HomeScreenEvent) -> Unit,
    onNavigate: (route: String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "TicTacToe!") },
                actions = {
                    IconButton(
                        onClick = { onNavigate(Routes.HistoryScreen.route) },
                        content = {
                            Icon(
                                imageVector = Icons.Default.History,
                                contentDescription = "Games History"
                            )
                        }
                    )
                }
            )
        }
    ) { values ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = values,
            content = {
                item {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 8.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ScoreBoard(
                            matchData = MatchData(
                                playerOne = state.playerOne,
                                playerOneScore = state.playerOneScore,
                                playerTwo = state.playerTwo,
                                playerTwoScore = state.playerTwoScore
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Greetings(greeting = "Greetings!")
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
                items(3) { row ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                    ) {
                        BoardSquare(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp),
                            board = state.board,
                            row = row,
                            column = 0,
                            playerTurn = state.isPlaying,
                            onClick = { board ->
                                onEvent(HomeScreenEvent.OnBoardClick(board))
                            }
                        )
                        BoardSquare(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp),
                            board = state.board,
                            row = row,
                            column = 1,
                            playerTurn = state.isPlaying,
                            onClick = { boardData ->
                                onEvent(HomeScreenEvent.OnBoardClick(boardData))
                            }
                        )
                        BoardSquare(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp),
                            board = state.board,
                            row = row,
                            column = 2,
                            playerTurn = state.isPlaying,
                            onClick = { board ->
                                onEvent(HomeScreenEvent.OnBoardClick(board))
                            }
                        )
                    }
                }
                item {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(text = "It's a draw!")
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                modifier = Modifier.weight(1f),
                                onClick = { onEvent(HomeScreenEvent.OnResetGame) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red,
                                    contentColor = Color.White
                                )
                            ) {
                                Text(text = "Reset game")
                            }
                            Button(
                                modifier = Modifier.weight(1f),
                                onClick = { onEvent(HomeScreenEvent.OnNewGame) },
                            ) {
                                Text(text = "New game")
                            }
                        }
                    }
                }
            }
        )
    }
}