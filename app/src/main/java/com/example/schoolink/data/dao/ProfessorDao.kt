package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.schoolink.data.entities.ProfessorEntity

@Dao
interface ProfessorDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createProfessor(professor: ProfessorEntity)

    @Update
    suspend fun updateProfessor(professor: ProfessorEntity)

    @Query("SELECT * FROM professors WHERE email = :email")
    suspend fun getProfessorByEmail(email: String): ProfessorEntity?
}