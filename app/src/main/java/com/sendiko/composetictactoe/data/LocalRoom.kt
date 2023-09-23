package com.sendiko.composetictactoe.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sendiko.composetictactoe.ui.screens.history.MatchData

@Database(
    entities = [MatchData::class],
    version = 1,
//    autoMigrations = [AutoMigration(from = 1, to = 2)]
)
abstract class LocalRoom: RoomDatabase() {

    abstract val dao: MatchHistoryDao

     companion object {
         @Volatile
         private var INSTANCE: LocalRoom?= null

        fun getInstance(context: Context): LocalRoom {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalRoom::class.java,
                    "match_history.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
     }
}