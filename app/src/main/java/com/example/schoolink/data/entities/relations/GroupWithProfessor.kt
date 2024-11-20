package com.example.schoolink.data.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.data.entities.refference.GroupProfessorCrossRef
import com.example.schoolink.data.entities.ProfessorEntity

data class GroupWithProfessor(
    @Embedded val group: GroupEntity,
    @Relation(
        parentColumn = "groupId",
        entityColumn = "professorId",
        associateBy = Junction(GroupProfessorCrossRef::class)
    )
    val professors: List<ProfessorEntity>
)
