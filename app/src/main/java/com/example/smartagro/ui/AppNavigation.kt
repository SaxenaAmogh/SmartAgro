package com.example.smartagro.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartagro.ui.screens.LoginPage

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("splash") {
            //SplashScreen(navController)
        }
        composable("login") {
            LoginPage(navController)
        }
    }
}