package com.example.xmis_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.xmis_project.ui.theme.ui.screens.DataScreen
import com.example.xmis_project.ui.theme.ui.screens.WelcomeScreen

object Destinations {
    const val WELCOME = "welcome"
    const val CHAT = "chat"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.WELCOME
    ) {
        composable(Destinations.WELCOME) {
            WelcomeScreen(
                onNavigateToChat = {
                    navController.navigate(Destinations.CHAT)
                }
            )
        }

        composable(Destinations.CHAT) {
            DataScreen()
        }
    }
}