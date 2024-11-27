package com.example.schoolink.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.components.miscellaneous.TopContentWithBackground
import com.example.schoolink.ui.viewmodels.ProfessorViewModel
import com.example.schoolink.ui.screens.main.content.ManagementContent
import com.example.schoolink.ui.screens.main.content.ScheduleContent

@Composable
fun MainScreen(
    email: String,
    selectedTab: Screen,
    professorViewModel: ProfessorViewModel,
    onStudent: () -> Unit,
    onGroup: () -> Unit
) {

    val screens = listOf(Screen.Home, Screen.Schedule, Screen.History, Screen.Manage)
    var selectedScreen by remember { mutableStateOf(selectedTab) }
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
                    ) {
                        when (selectedScreen) {
                            Screen.Home -> HomeContent()
                            Screen.Schedule -> ScheduleContent()
                            Screen.History -> HistoryContent()
                            Screen.Manage -> ManagementContent(
                                onStudent = onStudent,
                                onGroup = onGroup
                            )

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
fun HistoryContent() {
    Text(
        "Presence Screen", modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}


