package com.sendiko.composetictactoe.ui.screens.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class MatchData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val playerOne: String,
    val playerOneScore: Int,
    val playerTwo: String,
    val playerTwoScore: Int,
)
