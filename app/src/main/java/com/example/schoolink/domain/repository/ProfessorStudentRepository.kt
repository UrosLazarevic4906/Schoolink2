package com.example.schoolink.domain.repository

import com.example.schoolink.data.dao.ProfessorStudentDao
import com.example.schoolink.data.entities.refference.ProfessorStudentCrossRef
import com.example.schoolink.data.entities.relations.ProfessorWithStudents

class ProfessorStudentRepository(private val professorStudentDao: ProfessorStudentDao) {

    suspend fun addStudentToProfessor(crossRef: ProfessorStudentCrossRef) {
        professorStudentDao.insertProfessorStudentCrossRef(crossRef)
    }

    suspend fun getProfessorWithStudents(professorId: Int): ProfessorWithStudents? {
        return professorStudentDao.getProfessorWithStudents(professorId)
    }

    suspend fun removeStudentFromProfessor(professorId: Int, studentId: Int) {
        professorStudentDao.removeStudentFromProfessor(professorId, studentId)
    }
}