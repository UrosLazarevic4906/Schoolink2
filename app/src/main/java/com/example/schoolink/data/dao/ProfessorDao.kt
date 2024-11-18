package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.schoolink.data.entities.ProfessorEntity
import com.example.schoolink.data.entities.ProfessorStudentCrossRef
import com.example.schoolink.data.entities.relations.ProfessorWithStudents

@Dao
interface ProfessorDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createProfessor(professor: ProfessorEntity)

    @Insert
    suspend fun insertProfessorStudentCrossRef(crossRef: ProfessorStudentCrossRef)

    @Update
    suspend fun updateProfessor(professor: ProfessorEntity)

    @Query("SELECT * FROM professors WHERE email = :email")
    suspend fun getProfessorByEmail(email: String): ProfessorEntity?

    @Transaction
    @Query("SELECT * FROM professors WHERE id = :professorId")
    suspend fun getProfessorWithStudents(professorId: Int): ProfessorWithStudents


}