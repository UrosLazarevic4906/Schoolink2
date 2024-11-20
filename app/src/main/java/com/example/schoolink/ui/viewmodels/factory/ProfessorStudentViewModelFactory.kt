package com.example.schoolink.ui.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.schoolink.domain.repository.ProfessorStudentRepository
import com.example.schoolink.ui.viewmodels.ProfessorStudentViewModel

class ProfessorStudentViewModelFactory(
    private val repository: ProfessorStudentRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfessorStudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfessorStudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
