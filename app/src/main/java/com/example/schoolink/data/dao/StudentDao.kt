package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.schoolink.data.entities.StudentEntity

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(student: StudentEntity): Long

    @Query("SELECT * FROM students WHERE email = :email")
    suspend fun getStudentByEmail(email: String): StudentEntity?

    @Query("SELECT * FROM students WHERE studentCode = :studentCode")
    suspend fun getStudentByCode(studentCode: String): StudentEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM students WHERE studentCode = :studentCode)")
    suspend fun studentWithCodeExists(studentCode: String): Boolean
}