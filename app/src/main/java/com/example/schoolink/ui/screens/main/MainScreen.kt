@file:Suppress("DEPRECATION")

package com.example.schoolink.ui.screens.main

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.components.miscellaneous.TopContentWithBackground
import com.example.schoolink.ui.viewmodels.ProfessorViewModel
import com.example.schoolink.ui.screens.main.content.ManagementContent
import com.example.schoolink.ui.screens.main.content.ScheduleContent

@Composable
fun MainScreen(
    email: String,
    professorViewModel: ProfessorViewModel
) {

    val screens = listOf(Screen.Home, Screen.Presence, Screen.Manage, Screen.Schedule)
    var selectedScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    var professor by remember { mutableStateOf<ProfessorModel?>(null) }


    LaunchedEffect(email) {
        professorViewModel.getProfessorByEmail(email) { prof ->
            prof?.let {
                professor = it
            }
        }
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
                    TopContentWithBackground(selectedScreen, professor)

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
//                            .padding(top = 200.dp)
                    ) {
                        when (selectedScreen) {
                            Screen.Home -> HomeContent()
                            Screen.Presence -> PresenceContent()
                            Screen.Manage -> ManagementContent()
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
            .padding(top = 200.dp)
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


