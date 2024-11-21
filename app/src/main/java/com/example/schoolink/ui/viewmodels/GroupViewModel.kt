package com.example.schoolink.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolink.data.entities.relations.GroupWithStudents
import com.example.schoolink.domain.models.GroupModel
import com.example.schoolink.domain.repository.GroupRepository
import kotlinx.coroutines.launch

class GroupViewModel(
    private val repository: GroupRepository
) : ViewModel(){

    fun createGroup(
        group: GroupModel,
        professorId: Int,
        studentIds: List<Int>,
    ) {
       viewModelScope.launch {
           val groupId = repository.createGroup(group).toInt()
           repository.linkGroupWithProfessor(groupId, professorId)
           repository.linkGroupWithStudents(groupId, studentIds)
       }
    }

    fun getGroupWithStudents(groupId: Int, onResult: (GroupWithStudents?) -> Unit) {
        viewModelScope.launch {
            val groupWithStudents = repository.getGroupWithStudents(groupId)
            onResult(groupWithStudents)
        }
    }
}