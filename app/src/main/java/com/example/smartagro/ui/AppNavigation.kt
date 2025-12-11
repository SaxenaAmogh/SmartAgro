package com.example.smartagro.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartagro.ui.screens.CropPage
import com.example.smartagro.ui.screens.HomePage
import com.example.smartagro.ui.screens.LoginPage
import com.example.smartagro.ui.screens.SelectCropPage
import com.example.smartagro.ui.screens.WeatherPage

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "select"
    ) {
        composable("splash") {
            //SplashScreen(navController)
        }
        composable("login") {
            LoginPage(navController)
        }
        composable("home") {
            HomePage(navController)
        }
        composable("weather") {
            WeatherPage(navController)
        }
        composable("crop") {
            CropPage(navController)
        }
        composable("select") {
            SelectCropPage(navController)
        }

    }
}