package com.example.schoolink.ui.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.schoolink.domain.repository.GroupProfessorRepository
import com.example.schoolink.ui.viewmodels.GroupProfessorViewModel

class GroupProfessorViewModelFactory(
    private val repository: GroupProfessorRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroupProfessorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GroupProfessorViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
