package com.example.schoolink.ui.screens.main

import androidx.activity.compose.BackHandler
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
import com.example.schoolink.ui.screens.management.ManagementContent
import com.example.schoolink.ui.screens.schedule.ScheduleContent
import com.example.schoolink.ui.viewmodels.GroupProfessorViewModel
import com.example.schoolink.ui.viewmodels.LessonGroupViewModel
import com.example.schoolink.ui.viewmodels.LessonProfessorViewModel
import com.example.schoolink.ui.viewmodels.LessonViewModel

@Composable
fun MainScreen(
    email: String,
    professorViewModel: ProfessorViewModel,
    groupProfessorViewModel: GroupProfessorViewModel,
    lessonViewModel: LessonViewModel,
    lessonProfessorViewModel: LessonProfessorViewModel,
    lessonGroupViewModel: LessonGroupViewModel,
    onStudent: () -> Unit,
    onGroup: () -> Unit,
    onProfile: () -> Unit
) {

    val screens = listOf(Screen.Home, Screen.Schedule, Screen.History, Screen.Manage)
    var selectedScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    var professor by remember { mutableStateOf<ProfessorModel?>(null) }

    BackHandler {

    }

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
                    TopContentWithBackground(selectedScreen, professor, onProfile)

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        when (selectedScreen) {
                            Screen.Home -> HomeContent()
                            Screen.Schedule -> ScheduleContent(
                                professorViewModel = professorViewModel,
                                groupProfessorViewModel = groupProfessorViewModel,
                                lessonViewModel = lessonViewModel,
                                lessonProfessorViewModel = lessonProfessorViewModel,
                                lessonGroupViewModel = lessonGroupViewModel
                            )
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


