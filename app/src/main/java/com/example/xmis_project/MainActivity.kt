package com.example.xmis_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.xmis_project.ui.theme.ui.screens.AddReviewScreen
import com.example.xmis_project.ui.theme.ui.screens.DataScreen
import com.example.xmis_project.ui.theme.ui.screens.WelcomeScreen
import java.net.URLDecoder
import java.net.URLEncoder

object Destinations {
    const val WELCOME = "welcome"
    const val CHAT = "chat/{userPrompt}"
    const val REVIEW = "addReview"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
                onNavigateToChat = { userInput ->
                    val route = "chat/${URLEncoder.encode(userInput, "UTF-8")}"
                    navController.navigate(route)
                },
                onNavigateToReview = {
                    navController.navigate("addReview")
                }
            )
        }


        composable(
            route = Destinations.CHAT,
            arguments = listOf(
                navArgument("userPrompt") {
                    type = NavType.StringType // Define the argument type
                    defaultValue = "" // Optional: provide a default value
                    nullable = true // Optional: set to true if the value can be null
                }
            )
        ) { backStackEntry ->
            val userPrompt = backStackEntry.arguments?.getString("userPrompt") ?: ""
            val decodedPrompt = URLDecoder.decode(userPrompt, "UTF-8")

            DataScreen(
                navController = navController,
                initialUserPrompt = decodedPrompt
            )
        }


        composable(Destinations.REVIEW) {
            AddReviewScreen(navController = navController)
        }

    }
}