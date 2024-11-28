package com.example.schoolink.data.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.schoolink.data.entities.LessonEntity
import com.example.schoolink.data.entities.ProfessorEntity
import com.example.schoolink.data.entities.refference.LessonProfessorCrossRef

data class LessonWithProfessor(
    @Embedded val professor: ProfessorEntity,
    @Relation(
        parentColumn = "professorId",
        entityColumn = "lessonId",
        associateBy = Junction(LessonProfessorCrossRef::class)
    )
    val lessons: List<LessonEntity>
)
