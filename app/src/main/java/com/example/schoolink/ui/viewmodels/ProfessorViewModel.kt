package com.example.schoolink.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolink.data.entities.relations.ProfessorWithStudents
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.domain.repository.ProfessorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfessorViewModel(private val repository: ProfessorRepository) : ViewModel() {

    var currentProfessor: ProfessorModel? = null

    fun getProfessorByEmail(email: String, onResult: (ProfessorModel?) -> Unit) {
        viewModelScope.launch {
            val professor = withContext(Dispatchers.IO) {
                repository.getProfessorByEmail(email)
            }
            currentProfessor = professor
            onResult(professor)
        }
    }

    fun updateProfessor(professor: ProfessorModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProfessor(professor)
        }
    }

    fun createProfessor(professor: ProfessorModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createProfessor(professor)
        }
    }

    fun addStudentToProfessor(professorId: Int, studentId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudentToProfessor(professorId, studentId)
        }
    }

    fun getProfessorWithStudents(professorId: Int, onResult: (ProfessorWithStudents?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getProfessorWithStudents(professorId)
            onResult(result)
        }
    }


}
