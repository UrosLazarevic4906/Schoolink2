package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.schoolink.data.entities.refference.LessonProfessorCrossRef
import com.example.schoolink.data.entities.relations.LessonWithProfessor

@Dao
interface LessonProfessorDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLessonProfessorCrossRef(crossRef: LessonProfessorCrossRef)

    @Transaction
    @Query("SELECT * FROM professors WHERE professorId = :professorId")
    suspend fun getLessonsWithProfessor(professorId: Int): LessonWithProfessor?

    @Transaction
    @Query("DELETE FROM lesson_professor WHERE  lessonId = :lessonId AND professorId = :professorId")
    suspend fun removeLesonFromProfessor(lessonId: Int, professorId: Int)
}