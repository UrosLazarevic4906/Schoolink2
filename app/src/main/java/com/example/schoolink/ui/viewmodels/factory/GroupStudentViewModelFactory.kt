package com.example.schoolink.ui.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.schoolink.domain.repository.GroupStudentRepository
import com.example.schoolink.ui.viewmodels.GroupStudentViewModel

class GroupStudentViewModelFactory(
    private val repository: GroupStudentRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroupStudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GroupStudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
