package com.example.schoolink.domain.repository

import com.example.schoolink.data.dao.StudentDao
import com.example.schoolink.data.mappers.StudentMapper
import com.example.schoolink.domain.models.StudentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentRepository(
    private val studentDao: StudentDao
) {

    private val charset = ('A'..'Z') + ('0'..'9')

    private suspend fun generateStudentCode(): String {
        var newCode: String
        do {
            newCode = (1..8)
                .map { charset.random() }
                .joinToString("" )
        } while (studentDao.studentWithCodeExists(newCode))
        return newCode
    }

    suspend fun insertStudent(studentModel: StudentModel): Long {
        val entity =
            StudentMapper.fromModelToEntity(studentModel.copy(studentCode = generateStudentCode()))
        return studentDao.insertStudent(entity)
    }

    suspend fun getStudentByEmail(email: String): StudentModel? {
        val entity = studentDao.getStudentByEmail(email)
        return entity?.let { StudentMapper.fromEntityToModel(it) }
    }

    suspend fun getStudentByCode(code: String): StudentModel? {
        val entity = studentDao.getStudentByCode(code)
        return entity?.let { StudentMapper.fromEntityToModel(it) }
    }


}