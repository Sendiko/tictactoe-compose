package com.sendiko.composetictactoe.ui.screens.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.sendiko.composetictactoe.ui.components.ScoreBoard
import com.sendiko.composetictactoe.ui.screens.routes.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onNavigateBack: (route: String) -> Unit,
    state: HistoryScreenState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Match History")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onNavigateBack(Routes.HomeScreen.route) },
                        content = {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Navigate Back"
                            )
                        }
                    )
                }
            )
        }
    ) {
        LazyColumn(
            contentPadding = it,
            content = {
                items(state.matchHistory) { matchData ->
                    ScoreBoard(matchData = matchData)
                }
            }
        )
    }
}