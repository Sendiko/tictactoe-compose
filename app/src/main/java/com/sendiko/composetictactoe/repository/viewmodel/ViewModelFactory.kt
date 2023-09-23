package com.sendiko.composetictactoe.repository.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory private constructor(private val app: Application):
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        var INSTANCE: ViewModelFactory?= null

        @JvmStatic
        fun getInstance(app: Application): ViewModelFactory {
            when(INSTANCE){
                null -> synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(app)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> HistoryViewModel(app) as T
            modelClass.isAssignableFrom(HomeScreenViewModel::class.java) -> HomeScreenViewModel(app) as T
            else -> throw IllegalArgumentException("Unknown modelClass: " + modelClass.name)
        }
    }
}