package com.example.schoolink.domain.repository

import com.example.schoolink.data.dao.ProfessorDao
import com.example.schoolink.data.entities.ProfessorStudentCrossRef
import com.example.schoolink.data.entities.relations.ProfessorWithStudents
import com.example.schoolink.data.mappers.ProfessorMapper
import com.example.schoolink.domain.models.ProfessorModel

class ProfessorRepository(
    private val professorDao: ProfessorDao
) {
    suspend fun createProfessor(professorModel: ProfessorModel) {
        val entity = ProfessorMapper.fromModelToEntity(professorModel)
        professorDao.createProfessor(entity)
    }


    suspend fun addStudentToProfessor(professorId: Int, studentId: Int) {
        professorDao.insertProfessorStudentCrossRef(ProfessorStudentCrossRef(professorId, studentId))
    }

    suspend fun updateProfessor(professorModel: ProfessorModel) {
        val entity = ProfessorMapper.fromModelToEntity(professorModel)
        professorDao.updateProfessor(entity)
    }

    suspend fun getProfessorByEmail(email: String): ProfessorModel? {
        val entity = professorDao.getProfessorByEmail(email)
        return entity?.let { ProfessorMapper.fromEntityToModel(it) }
    }

    suspend fun getProfessorWithStudents(professorId: Int): ProfessorWithStudents? {
        return professorDao.getProfessorWithStudents(professorId)
    }
}