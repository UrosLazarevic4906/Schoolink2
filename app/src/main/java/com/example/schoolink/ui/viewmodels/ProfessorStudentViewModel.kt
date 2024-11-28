package com.example.schoolink.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolink.data.entities.refference.ProfessorStudentCrossRef
import com.example.schoolink.data.entities.relations.ProfessorWithStudents
import com.example.schoolink.domain.repository.ProfessorStudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfessorStudentViewModel(
    private val repository: ProfessorStudentRepository
) : ViewModel() {

    fun addStudentToProfessor(professorId: Int, studentId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudentToProfessor(ProfessorStudentCrossRef(professorId, studentId))
        }
    }

    fun getProfessorWithStudent(professorId: Int, onResult: (ProfessorWithStudents?) -> Unit) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getProfessorWithStudents(professorId)
            }
            onResult(result)
        }
    }

    fun removeStudentFromProfessor(professorId: Int, studentId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeStudentFromProfessor(professorId, studentId)
        }
    }
}