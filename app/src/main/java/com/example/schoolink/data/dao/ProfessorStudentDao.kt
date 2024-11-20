package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.schoolink.data.entities.refference.ProfessorStudentCrossRef
import com.example.schoolink.data.entities.relations.ProfessorWithStudents

@Dao
interface ProfessorStudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProfessorStudentCrossRef(crossRef: ProfessorStudentCrossRef)

    @Transaction
    @Query("SELECT * FROM professors WHERE professorId = :professorId")
    suspend fun getProfessorWithStudents(professorId: Int): ProfessorWithStudents?

    @Transaction
    @Query("DELETE FROM professor_students WHERE professorId = :professorId AND studentId = :studentId")
    suspend fun removeStudentFromProfessor(professorId: Int, studentId: Int)
}