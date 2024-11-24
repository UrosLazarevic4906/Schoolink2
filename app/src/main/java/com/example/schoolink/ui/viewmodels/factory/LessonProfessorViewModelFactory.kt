package com.example.schoolink.ui.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.schoolink.domain.repository.LessonProfessorRepository
import com.example.schoolink.ui.viewmodels.LessonProfessorViewModel

class LessonProfessorViewModelFactory(
    private val repository: LessonProfessorRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LessonProfessorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LessonProfessorViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
