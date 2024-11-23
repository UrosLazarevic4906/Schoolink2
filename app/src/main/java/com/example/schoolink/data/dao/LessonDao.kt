package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.schoolink.data.entities.LessonEntity

@Dao
interface LessonDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLesson(lesson: LessonEntity): Long

    @Delete
    suspend fun deleteLesson(lesson: LessonEntity)

    @Query("SELECT * FROM lessons WHERE lessonId = :lessonId")
    suspend fun getLessonById(lessonId: Int): LessonEntity?

//    @Query("SELECT * FROM lessons WHERE date = :date")
//    suspend fun getLessonsByDate(date: LocalDate): List<LessonEntity>
}