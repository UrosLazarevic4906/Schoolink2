package com.example.schoolink.data.entities.refference

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.schoolink.data.entities.LessonEntity
import com.example.schoolink.data.entities.ProfessorEntity

@Entity(
    tableName = "lesson_professor",
    primaryKeys = ["lessonId", "professorId"],
    foreignKeys = [
        ForeignKey(
            entity = LessonEntity::class,
            parentColumns = ["lessonId"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProfessorEntity::class,
            parentColumns = ["professorId"],
            childColumns = ["professorId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LessonProfessorCrossRef(
    val lessonId: Int,
    val professorId: Int
)
