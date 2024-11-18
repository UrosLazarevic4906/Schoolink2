package com.example.schoolink.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "professor_students",
    primaryKeys = ["professorId", "studentId"],
    foreignKeys = [
        ForeignKey(
            entity = ProfessorEntity::class,
            parentColumns = ["professorId"],
            childColumns = ["professorId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["studentId"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["professorId"]), Index(value = ["studentId"])]
)
data class ProfessorStudentCrossRef(
    val professorId: Int,
    val studentId: Int
)
