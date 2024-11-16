package com.example.schoolink.domain.repository

import com.example.schoolink.data.dao.StudentDao
import com.example.schoolink.data.mappers.StudentMapper
import com.example.schoolink.domain.models.StudentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentRepository(
    private val studentDao: StudentDao
) {

    private var nextStudentCode = 1000000

    suspend fun insertStudent(studentModel: StudentModel) {
        val entity = StudentMapper.fromModelToEntity(studentModel.copy(studentCode = generateStudentCode()))
        studentDao.insertStudent(entity)
    }

    suspend fun getStudentByEmail(email: String): StudentModel? {
        val entity = studentDao.getStudentByEmail(email)
        return entity?.let { StudentMapper.fromEntityToModel(it) }
    }
    suspend fun getStudentByCode(code: String): StudentModel? {
        val entity = studentDao.getStudentByCode(code)
        return entity?.let { StudentMapper.fromEntityToModel(it) }
    }

    private suspend fun generateStudentCode(): String {
        return withContext(Dispatchers.IO) {
            val currentMaxCode = studentDao.getMaxStudentCode()?.toIntOrNull() ?: (nextStudentCode - 1)
            (currentMaxCode + 1).coerceAtMost(9999999).toString()
        }
    }
}