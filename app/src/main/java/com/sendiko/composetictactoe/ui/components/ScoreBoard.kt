package com.sendiko.composetictactoe.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sendiko.composetictactoe.ui.screens.history.MatchData
import com.sendiko.composetictactoe.ui.theme.Typography

@Composable
fun ScoreBoard(
    matchData: MatchData
) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = matchData.playerOne,
                style = Typography.bodyMedium
            )
            Row {
                Text(
                    text = matchData.playerOneScore.toString(),
                    style = Typography.bodyMedium
                )
                Text(
                    text = ":",
                    style = Typography.bodyMedium
                )
                Text(
                    text = matchData.playerTwoScore.toString(),
                    style = Typography.bodyMedium
                )
            }
            Text(
                text = matchData.playerTwo,
                style = Typography.bodyMedium
            )
        }
    }
}