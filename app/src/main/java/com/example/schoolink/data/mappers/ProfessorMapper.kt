package com.example.schoolink.data.mappers

import com.example.schoolink.data.entities.ProfessorEntity
import com.example.schoolink.domain.models.ProfessorModel

object ProfessorMapper {

    fun fromEntityToModel(entity: ProfessorEntity): ProfessorModel {
        return ProfessorModel(
            id = entity.professorId,
            email = entity.email,
            password = entity.password,
            profilePicturePath = entity.profilePicturePath,
            firstName = entity.firstName,
            lastName = entity.lastName,
            gender = entity.gender,
            dateOfBirth = entity.dateOfBirth
        )
    }

    fun fromModelToEntity(model: ProfessorModel): ProfessorEntity {
        return ProfessorEntity(
            professorId = model.id,
            email = model.email,
            password = model.password,
            profilePicturePath = model.profilePicturePath,
            firstName = model.firstName,
            lastName = model.lastName,
            gender = model.gender,
            dateOfBirth = model.dateOfBirth
        )
    }
}