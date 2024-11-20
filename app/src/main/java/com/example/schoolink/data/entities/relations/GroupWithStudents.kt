package com.example.schoolink.data.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.data.entities.refference.GroupStudentCrossRef
import com.example.schoolink.data.entities.StudentEntity

data class GroupWithStudents(
    @Embedded val group: GroupEntity,
    @Relation(
        parentColumn = "groupId",
        entityColumn = "studentId",
        associateBy = Junction(GroupStudentCrossRef::class)
    )
    val students: List<StudentEntity>
)
