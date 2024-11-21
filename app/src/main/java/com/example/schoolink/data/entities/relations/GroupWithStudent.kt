package com.example.schoolink.data.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.data.entities.StudentEntity
import com.example.schoolink.data.entities.refference.GroupStudentCrossRef

data class GroupWithStudent(
    @Embedded val group: GroupEntity,
    @Relation(
        parentColumn = "groupId",
        entityColumn = "studentId",
        associateBy = Junction(GroupStudentCrossRef::class)
    )
    val students: List<StudentEntity>
)
