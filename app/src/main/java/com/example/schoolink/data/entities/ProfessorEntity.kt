package com.example.schoolink.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.schoolink.domain.models.Gender

@Entity(
    tableName = "professors",
    indices = [Index(value = ["email"], unique = true)]
)
data class ProfessorEntity(
    val email: String,
    val password: String?,
    val profilePicturePath: String?,
    val firstName: String? = null,
    val lastName: String? = null,
    val gender: Gender? = null,
    val dateOfBirth: String? = null,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)