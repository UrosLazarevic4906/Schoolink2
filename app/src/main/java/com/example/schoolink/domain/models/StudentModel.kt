package com.example.schoolink.domain.models

data class StudentModel(
    val id: Int = 0,
    val email: String,
    val profilePicturePath: String?,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val dateOfBirth: String,
    val studentCode: String = "",
    val description: String
)
