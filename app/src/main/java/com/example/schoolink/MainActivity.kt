package com.example.schoolink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.schoolink.data.database.AppDatabase
import com.example.schoolink.domain.repository.ProfessorRepository
import com.example.schoolink.domain.repository.StudentRepository
import com.example.schoolink.ui.navigation.AppNavigation
import com.example.schoolink.ui.theme.SchoolinkTheme
import com.example.schoolink.ui.viewmodels.factory.ProfessorViewModelFactory
import com.example.schoolink.ui.viewmodels.factory.StudentViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getInstance(this)

        val professorRepository = ProfessorRepository(database.professorDao())

        val professorViewModelFactory = ProfessorViewModelFactory(professorRepository)

        setContent {
            SchoolinkTheme {
                AppNavigation(
                    professorViewModelFactory = professorViewModelFactory,
                )
            }
        }
    }
}
