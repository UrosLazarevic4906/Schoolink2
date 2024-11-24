package com.example.schoolink.domain.models

import java.time.LocalDate
import java.time.LocalTime

data class LessonModel(
    val lessonId: Int = 0,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime
)
