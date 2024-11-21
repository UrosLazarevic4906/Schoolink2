package com.example.schoolink.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolink.data.entities.refference.GroupStudentCrossRef
import com.example.schoolink.data.entities.relations.GroupWithStudent
import com.example.schoolink.domain.repository.GroupStudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GroupStudentViewModel(
    private val repository: GroupStudentRepository
) : ViewModel(){

    fun addStudentToGroup(groupId: Int, studentId: Int) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addStudentToGroup(GroupStudentCrossRef(groupId, studentId))
        }
    }

    fun getStudentsWithGroup(groupId: Int, onResult: (GroupWithStudent?) -> Unit) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getGroupWithStudents(groupId)
            }
            onResult(result)
        }
    }

    fun removeStudentFromGroup(groupId: Int, studentId: Int) {
        viewModelScope.launch(Dispatchers.IO){
            repository.removeStudentFromGroup(groupId, studentId)
        }
    }
}