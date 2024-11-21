package com.example.schoolink.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolink.data.entities.refference.GroupProfessorCrossRef
import com.example.schoolink.data.entities.relations.GroupWithProfessor
import com.example.schoolink.domain.repository.GroupProfessorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GroupProfessorViewModel (
    private val repository: GroupProfessorRepository
) : ViewModel(){

    fun addGroupToProfessor(groupId: Int, professorId: Int) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addGroupToProfessor(GroupProfessorCrossRef(groupId, professorId))
        }
    }

    fun getGroupsWithProfessor(professorId: Int, onResult: (GroupWithProfessor?) -> Unit){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getGroupsWithProfessor(professorId)
            }
            onResult(result)
        }
    }

    fun removeGroupFromProfessor(groupId: Int, professorId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeGroupFromProfessor(groupId, professorId)
        }
    }
}