package com.sendiko.composetictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sendiko.composetictactoe.ui.screens.history.HistoryScreen
import com.sendiko.composetictactoe.ui.screens.history.HistoryViewModel
import com.sendiko.composetictactoe.ui.screens.home.HomeScreen
import com.sendiko.composetictactoe.ui.screens.home.HomeScreenViewModel
import com.sendiko.composetictactoe.ui.screens.routes.Routes
import com.sendiko.composetictactoe.ui.theme.ComposeTicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        setContent {
            ComposeTicTacToeTheme {
                val navController = rememberNavController()
                val homeScreenViewModel by viewModels<HomeScreenViewModel>()
                val historyScreenViewModel by viewModels<HistoryViewModel>()
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