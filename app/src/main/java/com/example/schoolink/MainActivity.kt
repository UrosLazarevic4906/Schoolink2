package com.example.schoolink

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.schoolink.data.database.AppDatabase
import com.example.schoolink.domain.repository.ProfessorRepository
import com.example.schoolink.domain.repository.ProfessorStudentRepository
import com.example.schoolink.domain.repository.StudentRepository
import com.example.schoolink.ui.navigation.AppNavigation
import com.example.schoolink.ui.theme.SchoolinkTheme
import com.example.schoolink.ui.viewmodels.factory.ProfessorStudentViewModelFactory
import com.example.schoolink.ui.viewmodels.factory.ProfessorViewModelFactory
import com.example.schoolink.ui.viewmodels.factory.StudentViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getInstance(this)

        val professorRepository = ProfessorRepository(database.professorDao())
        val studentRepository = StudentRepository(database.studentDao())
        val professorStudentRepository = ProfessorStudentRepository(database.professorStudentDao())

        val professorViewModelFactory = ProfessorViewModelFactory(professorRepository)
        val studentViewModelFactory = StudentViewModelFactory(studentRepository)
        val professorStudentViewModelFactory = ProfessorStudentViewModelFactory(professorStudentRepository)


        setContent {
            SchoolinkTheme {
                AppNavigation(
                    professorViewModelFactory = professorViewModelFactory,
                    studentViewModelFactory = studentViewModelFactory,
                    professorStudentViewModelFactory = professorStudentViewModelFactory
                )
            }
        }
    }
}
