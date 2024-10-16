package com.launcher.min.view.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.launcher.min.core.ui.theme.MinLauncherTheme
import com.launcher.min.feature.home.presentation.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MinLauncherTheme {
                HomeScreen()
            }
        }
    }
}