package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.schoolink.data.entities.refference.LessonGroupCrossRef
import com.example.schoolink.data.entities.relations.LessonWithGroup

@Dao
interface LessonGroupDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLessonGroupCrossRef(crossRef: LessonGroupCrossRef)

    @Transaction
    @Query("SELECT * FROM lessons WHERE lessonId = :lessonId")
    suspend fun getLessonWithGroup(lessonId: Int): LessonWithGroup?

    @Transaction
    @Query("DELETE FROM lesson_group WHERE lessonId = :lessonId AND groupId = :groupId")
    suspend fun removeLessonWithGroup(lessonId: Int, groupId: Int)
}