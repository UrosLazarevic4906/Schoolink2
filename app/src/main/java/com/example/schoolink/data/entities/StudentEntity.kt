package com.example.schoolink.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.schoolink.domain.models.Gender

@Entity(
    tableName = "students",
    indices = [
        Index(
            value = ["email"],
            unique = true
        ),
        Index(
            value = ["studentCode"],
            unique = true
        )
    ]
)
data class StudentEntity(
    val email: String,
    val profilePicturePath: String?,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val dateOfBirth: String,
    val studentCode: String,
    val description: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)