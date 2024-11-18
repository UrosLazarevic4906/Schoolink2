package com.example.schoolink.data.dao

import androidx.room.*
import com.example.schoolink.data.entities.ProfessorEntity
import com.example.schoolink.data.entities.ProfessorStudentCrossRef
import com.example.schoolink.data.entities.relations.ProfessorWithStudents

@Dao
interface ProfessorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createProfessor(professor: ProfessorEntity)

    @Update
    suspend fun updateProfessor(professor: ProfessorEntity)

    @Query("SELECT * FROM professors WHERE email = :email")
    suspend fun getProfessorByEmail(email: String): ProfessorEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProfessorStudentCrossRef(crossRef: ProfessorStudentCrossRef)

    @Transaction
    @Query("SELECT * FROM professors WHERE professorId = :professorId")
    suspend fun getProfessorWithStudents(professorId: Int): ProfessorWithStudents?

    @Query("DELETE FROM professor_students WHERE professorId = :professorId AND studentId = :studentId")
    suspend fun removeStudentFromProfessor(professorId: Int, studentId: Int)
}
