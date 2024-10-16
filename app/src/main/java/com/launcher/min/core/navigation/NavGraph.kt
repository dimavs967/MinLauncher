package com.launcher.min.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SetupNavGraph(navController: NavHostController) {

    val transitionDuration = 600

    NavHost(
        navController = navController,
        startDestination = HomeScreen
    ) {

    }
}