package com.example.schoolink.ui.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.schoolink.domain.repository.ProfessorRepository
import com.example.schoolink.ui.viewmodels.ProfessorViewModel

class ProfessorViewModelFactory(private val repository: ProfessorRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfessorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfessorViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
