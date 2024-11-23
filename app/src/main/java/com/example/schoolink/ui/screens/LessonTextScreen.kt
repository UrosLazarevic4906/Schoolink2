package com.example.schoolink.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import com.example.schoolink.domain.models.LessonModel
import com.example.schoolink.ui.components.inputs.DatePickerField
import com.example.schoolink.ui.components.inputs.TimePickerField
import com.example.schoolink.ui.viewmodels.LessonProfessorViewModel
import com.example.schoolink.ui.viewmodels.LessonViewModel
import com.example.schoolink.ui.viewmodels.ProfessorViewModel
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun LessonTestScreen(
    email: String,
    professorViewModel: ProfessorViewModel,
    lessonViewModel: LessonViewModel,
    lessonProfessorViewModel: LessonProfessorViewModel,
    onLessonCreated: () -> Unit
) {

    var date by remember { mutableStateOf(LocalDate.now()) }
    var startTime by remember { mutableStateOf(LocalTime.of(8, 0)) }
    var endTime by remember { mutableStateOf(LocalTime.of(10, 0)) }


    Column {
        DatePickerField(
            selectedDate = date,
            onDateSelected = { date = it }
        )
        TimePickerField(
            selectedTime = startTime,
            onTimeSelected = { startTime = it }
        )
        TimePickerField(
            selectedTime = endTime,
            onTimeSelected = { endTime = it }
        )
        Button(
            onClick = {
                val lesson = LessonModel(
                    date = date,
                    startTime = startTime,
                    endTime = endTime
                )
                professorViewModel.getProfessorByEmail(email) { professor ->
                    lessonViewModel.createLesson(lesson) { lessonId ->
                        if(lessonId > 0){
                            lessonProfessorViewModel.addLessonToProfessor(lessonId.toInt(), professor!!.id)
                        }

                        onLessonCreated()
                    }
                }

            }
        ) {
            Text("Create lesson")
        }
    }
}

