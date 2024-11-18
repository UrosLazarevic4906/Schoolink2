package com.example.schoolink.data.entities

import androidx.room.Entity

@Entity(primaryKeys = ["professorId", "studentId"])
data class ProfessorStudentCrossRef(
    val professorId: Int,
    val studentId: Int
)