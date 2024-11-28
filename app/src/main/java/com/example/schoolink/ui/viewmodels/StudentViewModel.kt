package com.example.schoolink.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.domain.models.StudentModel
import com.example.schoolink.domain.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentViewModel(private val repository: StudentRepository) : ViewModel() {

    var currentStudent: StudentModel? = null

    fun addStudent(student: StudentModel, onStudentAdded: (Long) -> Unit) {
        viewModelScope.launch {
            val studentId = withContext(Dispatchers.IO) {
                repository.insertStudent(student)
            }
            onStudentAdded(studentId)
        }
    }

    fun getStudentByEmail(
        email: String,
        onResult: (StudentModel?) -> Unit
    ) {
        viewModelScope.launch {
            val student = withContext(Dispatchers.IO) {
                repository.getStudentByEmail(email)
            }
            currentStudent = student
            onResult(student)
        }
    }

    fun updateStudentAsync(student: StudentModel, onComplete: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStudent(student)
            withContext(Dispatchers.Main) {
                onComplete()
            }
        }
    }

    suspend fun getStudentByEmailSync(email: String): StudentModel? {
        return withContext(Dispatchers.IO) {
            repository.getStudentByEmail(email)
        }
    }

    fun getStudentByCode(
        code: String,
        onResult: (StudentModel?) -> Unit
    ) {
        viewModelScope.launch {
            val student = withContext(Dispatchers.IO) {
                repository.getStudentByCode(code)
            }
            currentStudent = student
            onResult(student)
        }
    }


}