package com.example.schoolink.data.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.data.entities.LessonEntity
import com.example.schoolink.data.entities.refference.LessonGroupCrossRef

data class LessonWithGroup(
    @Embedded val lesson: LessonEntity,
    @Relation(
        parentColumn = "lessonId",
        entityColumn = "groupId",
        associateBy = Junction(LessonGroupCrossRef::class)
    )
    val group: GroupEntity
)
