package com.example.schoolink.ui.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.schoolink.domain.repository.GroupRepository
import com.example.schoolink.domain.repository.ProfessorRepository
import com.example.schoolink.ui.viewmodels.GroupViewModel
import com.example.schoolink.ui.viewmodels.ProfessorViewModel

class GroupViewModelFactory(private val repository: GroupRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroupViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GroupViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
