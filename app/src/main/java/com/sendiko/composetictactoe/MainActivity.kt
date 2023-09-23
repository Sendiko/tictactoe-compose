package com.sendiko.composetictactoe

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sendiko.composetictactoe.repository.viewmodel.HistoryViewModel
import com.sendiko.composetictactoe.repository.viewmodel.HomeScreenViewModel
import com.sendiko.composetictactoe.repository.viewmodel.ViewModelFactory
import com.sendiko.composetictactoe.ui.screens.history.HistoryScreen
import com.sendiko.composetictactoe.ui.screens.home.HomeScreen
import com.sendiko.composetictactoe.ui.screens.routes.Routes
import com.sendiko.composetictactoe.ui.theme.ComposeTicTacToeTheme


class MainActivity : ComponentActivity() {
    private fun <T : ViewModel> obtainViewModel(app: Application, modelClass: Class<T>): T {
        val factory = ViewModelFactory.getInstance(app)
        return ViewModelProvider(this, factory)[modelClass]
    }

    private val historyScreenViewModel: HistoryViewModel by lazy {
        obtainViewModel(requireNotNull(application), HistoryViewModel::class.java)
    }

    private val homeScreenViewModel: HomeScreenViewModel by lazy {
        obtainViewModel(requireNotNull(application), HomeScreenViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        setContent {
            ComposeTicTacToeTheme {
                val navController = rememberNavController()
                val homeScreenState by homeScreenViewModel.state.collectAsState()
                val historyScreenState by historyScreenViewModel.state.collectAsState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.HomeScreen.route,
                        builder = {
                            composable(
                                route = Routes.HomeScreen.route,
                                content = {
                                    HomeScreen(
                                        state = homeScreenState,
                                        onEvent = homeScreenViewModel::onEvent,
                                        onNavigate = {
                                            navController.navigate(it)
                                        }
                                    )
                                }
                            )
                            composable(
                                route = Routes.HistoryScreen.route,
                                content = {
                                    HistoryScreen(
                                        onNavigateBack = {
                                            navController.navigate(it)
                                        },
                                        state = historyScreenState
                                    )
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}