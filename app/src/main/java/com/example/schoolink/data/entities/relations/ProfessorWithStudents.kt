package com.example.schoolink.data.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.schoolink.data.entities.ProfessorEntity
import com.example.schoolink.data.entities.ProfessorStudentCrossRef
import com.example.schoolink.data.entities.StudentEntity

data class ProfessorWithStudents(
    @Embedded val professor: ProfessorEntity,
    @Relation(
        parentColumn = "professorId",
        entityColumn = "studentId",
        associateBy = Junction(ProfessorStudentCrossRef::class)
    )
    val students: List<StudentEntity>
)


//
//data class StudentWithProfessors(
//    @Embedded val student: StudentEntity,
//    @Relation(
//
//    )
//)