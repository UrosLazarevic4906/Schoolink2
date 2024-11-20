package com.example.schoolink.data.entities.refference

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.data.entities.StudentEntity

@Entity(
    tableName = "group_students",
    primaryKeys = ["groupId", "studentId"],
    foreignKeys = [
        ForeignKey(
            entity = GroupEntity::class,
            parentColumns = ["groupId"],
            childColumns = ["groupId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["studentId"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GroupStudentCrossRef(
    val groupId: Int,
    val studentId: Int
)
