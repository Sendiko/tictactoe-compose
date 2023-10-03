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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.composetictactoe.ui.components.BoardSquare
import com.sendiko.composetictactoe.ui.components.Greetings
import com.sendiko.composetictactoe.ui.components.ScoreBoard
import com.sendiko.composetictactoe.ui.screens.history.MatchData
import com.sendiko.composetictactoe.ui.screens.home.Players.*
import com.sendiko.composetictactoe.ui.screens.routes.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeScreenState,
    onEvent: (HomeScreenEvent) -> Unit,
    onNavigate: (route: String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
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
        var pon by rememberSaveable {
            mutableStateOf("")
        }
        var ptn by rememberSaveable {
            mutableStateOf("")
        }
        if (state.playerOneName == "" && state.playerTwoName == "") {
            ModalBottomSheet(
                onDismissRequest = {
                    coroutineScope.launch {
                        sheetState.expand()
                    }
                },
                sheetState = sheetState
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(modifier = Modifier.weight(1f), text = "Welcome to Compose TicTacToe!", fontSize = 20.sp)
                    IconButton(onClick = { onNavigate(Routes.HistoryScreen.route) }) {
                        Icon(imageVector = Icons.Default.History, contentDescription = "History")
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(modifier = Modifier.weight(1f), text = "Player one name")
                    OutlinedTextField(
                        modifier = Modifier.weight(2f),
                        value = pon,
                        singleLine = true,
                        shape = RoundedCornerShape(100),
                        onValueChange = {
                            pon = it
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(modifier = Modifier.weight(1f), text = "Player two name")
                    OutlinedTextField(
                        modifier = Modifier.weight(2f),
                        value = ptn,
                        singleLine = true,
                        shape = RoundedCornerShape(100),
                        onValueChange = {
                            ptn = it
                        }
                    )
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = pon != "" && ptn != "",
                    onClick = {
                        onEvent(HomeScreenEvent.OnPlayerOneNameInput(pon))
                        onEvent(HomeScreenEvent.OnPlayerTwoNameInput(ptn))
                        coroutineScope.launch { sheetState.hide() }
                    },
                    content = {
                        Text(text = "Done")
                    }
                )
            }
        }
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
                                playerOne = state.playerOneName,
                                playerOneScore = state.playerOneScore,
                                playerTwo = state.playerTwoName,
                                playerTwoScore = state.playerTwoScore
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Greetings(state = state)
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
                            playerTurn = state.playerTurn,
                            enabled = state.board[row][0] == ' ' && state.isWinning == null,
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
                            playerTurn = state.playerTurn,
                            enabled = state.board[row][1] == ' ' && state.isWinning == null,
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
                            playerTurn = state.playerTurn,
                            enabled = state.board[row][2] == ' ' && state.isWinning == null,
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
                        when {
                            state.isWinning == PlayerOne -> {
                                Card(
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        text = state.playerOneName + " has won the game!",
                                        style = TextStyle(
                                            textAlign = TextAlign.Start,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                }
                            }

                            state.isWinning == PlayerTwo -> {
                                Card(
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        text = state.playerTwoName + " has won the game!",
                                        style = TextStyle(
                                            textAlign = TextAlign.Start,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                }
                            }

                            state.isDraw -> {
                                Card(
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        text = "It's a draw!",
                                        style = TextStyle(
                                            textAlign = TextAlign.Start,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                }
                            }

                            state.isWinning == null -> Card {}
                            else -> Card {}
                        }
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
                                Text(text = "Restart game")
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