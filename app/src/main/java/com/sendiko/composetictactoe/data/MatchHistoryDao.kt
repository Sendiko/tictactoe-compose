package com.sendiko.composetictactoe.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sendiko.composetictactoe.ui.screens.history.MatchData
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchHistoryDao {
    @Insert
    suspend fun insertMatch(matchData: MatchData)

    @Query("select * from matches order by id desc")
    fun getMatches(): Flow<List<MatchData>>
}