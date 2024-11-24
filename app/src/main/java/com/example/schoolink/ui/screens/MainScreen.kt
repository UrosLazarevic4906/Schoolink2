package com.example.schoolink.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.schoolink.ui.components.miscellaneous.TopContentWithBackground
import com.example.schoolink.ui.screens.main.BottomNavigationBar
import com.example.schoolink.ui.screens.main.Screen
import com.example.schoolink.ui.theme.White
import com.example.schoolink.ui.theme.Yellow

@Composable
fun MainScreen() {

    val screens = listOf(Screen.Home, Screen.Presence, Screen.Manage, Screen.Schedule)
    var selectedScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    /*todo: proslediti context*/
    val context = LocalContext.current
    val activity = context as ComponentActivity
    val statusBarColor = selectedScreen.statuBarColor

    SideEffect {
        val window = activity.window
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = statusBarColor.toArgb()

        // Optionally, change the icon color (dark or light icons)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                screens = screens,
                selectedScreen = selectedScreen,
                onScreenSelected = { selectedScreen = it }
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Box(modifier = Modifier.fillMaxSize()) {
                    TopContentWithBackground(selectedScreen)

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 200.dp)
                    ) {
                        when (selectedScreen) {
                            Screen.Home -> HomeContent()
                            Screen.Presence -> PresenceContent()
                            Screen.Manage -> ManageContent()
                            Screen.Schedule -> ScheduleContent()
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun HomeContent() {
    Text(
        "Home Screen", modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}

@Composable
fun PresenceContent() {
    Text(
        "Presence Screen", modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}

@Composable
fun ManageContent() {
    Text(
        "Manage Screen", modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}

@Composable
fun ScheduleContent() {
    Text(
        "Schedule Screen", modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}