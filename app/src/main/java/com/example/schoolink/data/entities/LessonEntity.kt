package com.example.schoolink.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(
    tableName = "lessons"
)
data class LessonEntity(
    @PrimaryKey(autoGenerate = true)
    val lessonId: Int = 0,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime
)