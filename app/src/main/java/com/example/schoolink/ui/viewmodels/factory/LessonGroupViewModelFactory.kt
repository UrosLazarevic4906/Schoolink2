package com.example.schoolink.ui.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.schoolink.domain.repository.LessonGroupRepository
import com.example.schoolink.ui.viewmodels.LessonGroupViewModel

class LessonGroupViewModelFactory(
    private val repository: LessonGroupRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LessonGroupViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LessonGroupViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
