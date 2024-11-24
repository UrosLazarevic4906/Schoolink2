package com.example.schoolink.data.entities.refference

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.data.entities.LessonEntity


@Entity(
    tableName = "lesson_group",
    primaryKeys = ["lessonId", "groupId"],
    foreignKeys = [
        ForeignKey(
            entity = LessonEntity::class,
            parentColumns = ["lessonId"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GroupEntity::class,
            parentColumns = ["groupId"],
            childColumns = ["groupId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LessonGroupCrossRef(
    val lessonId: Int,
    val groupId: Int
)
