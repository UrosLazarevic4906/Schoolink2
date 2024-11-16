package com.example.schoolink.domain.models

data class ProfessorModel(
    val id: Int = 0,
    val email: String,
    val password: String? = null,
    val profilePicturePath: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val gender: Gender? = null,
    val dateOfBirth: String? = null
)
