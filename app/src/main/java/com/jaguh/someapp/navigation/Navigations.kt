package com.jaguh.someapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jaguh.someapp.StartScreen
import com.jaguh.someapp.Step2Screen
import com.jaguh.someapp.Step3Screen
import com.jaguh.someapp.data.AiChatScreen

@Composable
fun Navigations() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home.route) {
        composable(Home.route){
            StartScreen(navController)
        }
        composable(Step2.route){
            Step2Screen(navController)
        }
        composable(Step3.route){
            Step3Screen(navController)
        }
        composable(AI.route){
            AiChatScreen()
        }
    }
}