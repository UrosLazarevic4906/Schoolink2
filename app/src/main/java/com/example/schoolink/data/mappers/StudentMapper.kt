package com.example.schoolink.data.mappers

import com.example.schoolink.data.entities.StudentEntity
import com.example.schoolink.domain.models.StudentModel

object StudentMapper {

    fun fromEntityToModel(entity: StudentEntity): StudentModel {
        return StudentModel(
            id = entity.studentId,
            email = entity.email,
            firstName = entity.firstName,
            lastName = entity.lastName,
            dateOfBirth = entity.dateOfBirth,
            description = entity.description,
            gender = entity.gender,
            profilePicturePath = entity.profilePicturePath,
            studentCode = entity.studentCode
        )
    }

    fun fromModelToEntity(model: StudentModel): StudentEntity {
        return StudentEntity(
            studentId = model.id,
            email = model.email,
            firstName = model.firstName,
            lastName = model.lastName,
            dateOfBirth = model.dateOfBirth,
            description = model.description,
            gender = model.gender,
            profilePicturePath = model.profilePicturePath,
            studentCode = model.studentCode
        )
    }
}