package com.example.schoolink.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.schoolink.ui.screens.main.BottomNavigationBar
import com.example.schoolink.ui.screens.main.Screen

@Composable
fun MainScreen(){

    val screens = listOf(Screen.Home, Screen.Presence, Screen.Manage, Screen.Schedule, Screen.Messages)
    var selectedScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    Scaffold(
//        topBar = {
//            TopContent(screen = selectedScreen)
//        },
        bottomBar = {
            BottomNavigationBar(
                screens = screens,
                selectedScreen = selectedScreen,
                onScreenSelected = { selectedScreen = it }
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when (selectedScreen) {
                    Screen.Home -> HomeContent()
                    Screen.Presence -> PresenceContent()
                    Screen.Manage -> ManageContent()
                    Screen.Schedule -> ScheduleContent()
                    Screen.Messages -> MessagesContent()
                }
            }
        }
    )
}

@Composable
fun HomeContent() {
    Text("Home Screen", modifier = Modifier.fillMaxSize().padding(16.dp))
}

@Composable
fun PresenceContent() {
    Text("Presence Screen", modifier = Modifier.fillMaxSize().padding(16.dp))
}

@Composable
fun ManageContent() {
    Text("Manage Screen", modifier = Modifier.fillMaxSize().padding(16.dp))
}

@Composable
fun ScheduleContent() {
    Text("Schedule Screen", modifier = Modifier.fillMaxSize().padding(16.dp))
}

@Composable
fun MessagesContent() {
    Text("Messages Screen", modifier = Modifier.fillMaxSize().padding(16.dp))
}