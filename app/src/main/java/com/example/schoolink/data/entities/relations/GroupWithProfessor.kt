package com.example.schoolink.data.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.data.entities.ProfessorEntity
import com.example.schoolink.data.entities.refference.GroupProfessorCrossRef

data class GroupWithProfessor(
    @Embedded val professor: ProfessorEntity,
    @Relation(
        parentColumn = "professorId",
        entityColumn = "groupId",
        associateBy = Junction(GroupProfessorCrossRef::class)
    )
    val groups: List<GroupEntity>
)
