package com.example.schoolink.data.entities.refference

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.data.entities.ProfessorEntity

@Entity(
    tableName = "group_professor",
    primaryKeys = ["groupId", "professorId"],
    foreignKeys = [
        ForeignKey(
            entity = GroupEntity::class,
            parentColumns = ["groupId"],
            childColumns = ["groupId"],
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
data class GroupProfessorCrossRef(
    val groupId: Int,
    val professorId: Int
)
